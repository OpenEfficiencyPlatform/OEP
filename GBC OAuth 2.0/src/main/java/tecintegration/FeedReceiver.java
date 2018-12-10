package tecintegration;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.abdera.model.Content;
import org.apache.abdera.model.Element;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Link;

import tecintegration.IntervalBlock.IntervalReading;
import tecintegration.IntervalBlock.IntervalReading.TimePeriod;


public class FeedReceiver {
	public void processFeed(Feed feed) throws Exception {

		Meter meter = new Meter();
		
		for (Entry entry: feed.getEntries()){
			processFeed(meter, entry);
	    }
		
		String chckit = meter.getMeterName(); 
	}

	public void processFeed(Meter meter, Entry entry) throws JAXBException, UnsupportedEncodingException {
		String title = entry.getTitleElement().getValue();
		switch(title){
		case "Green Button Subscription Feed":
			List<Link> links = entry.getLinks();
			for (Link link : links){
				if (link.getRel().equals("self"))
				{	
					String wholeLink = link.getHref().toString();
					String[] parts = wholeLink.split("/");
					String acctNum = parts[parts.length-1];
					meter.setAccountNumber(acctNum);
				}
			}
			break;
		case "Interval Block":
		{
			List<Reading> readings = new ArrayList<Reading>();
			Content content = entry.getContentElement();
			
			JAXBContext jaxbContext = JAXBContext.newInstance("tecintegration");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			Element outer = content.getFirstChild();
			String inner = outer.toString();
			String intervalEncodedBetter = inner.replace("http://naesb.org/espi", "");
			InputStream is = new ByteArrayInputStream(intervalEncodedBetter.getBytes());
   			BufferedReader bfReader = new BufferedReader(new InputStreamReader(is));
   			IntervalBlock intervalBlock =  (IntervalBlock) unmarshaller.unmarshal(bfReader);
						
			IntervalReading[] reads =  intervalBlock.getIntervalReading();
			
			for (IntervalReading read : reads){
				Reading reading = new Reading();
				int consumption = read.getValue();
				TimePeriod tp = read.getTimePeriod();
				int unixEpochTime = tp.getStart();
				int duration = tp.getDuration();
				reading.setStartDate(new java.util.Date(unixEpochTime *1000));
   				reading.setEndDate(new java.util.Date((unixEpochTime + duration) *1000));
   				reading.setConsumption(new BigDecimal(consumption));
   				readings.add(reading);
			}
			meter.setReadings(readings);
			break;
		}
		case "Usage Summary":
		{
			List<Link> lnks = entry.getLinks();
			for (Link link : lnks){
				if (link.getRel().equals("self"))
				{	
					String wholeLink = link.getHref().toString();
					String[] parts = wholeLink.split("/");
					String cisrId = parts[parts.length-5];
					meter.setcisrID(cisrId);
				}
			}
			break;
		}
      
		}
	}
    	
}
