package tecintegration;

import static org.junit.Assert.*;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.abdera.model.Feed;
import org.junit.Test;

public class AccessFeedEntry {

	@Test
	public void AccessFeed() {
		
		InputStream in = this.getClass().getClassLoader()
                .getResourceAsStream("TestFeed.xml");
		
		assertEquals(true,in != null);
		
	}
	
	@Test
	public void ProcessAccountNum() {
		
		InputStream in = this.getClass().getClassLoader()
                .getResourceAsStream("TestFeed.xml");
		
		FeedReceiver fr = new FeedReceiver();
		try {
						
			fr.processFeed((Feed)in);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(true,in != null);
		
	}
	

}
