package com.psdconsulting;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// PM's metrics are retrievable in packets of 10. 

public class GetMetricsForProperty extends AbstractMessageTransformer{

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
	
		String response = "";
		String response2 = "";
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat dateformatMetric = new SimpleDateFormat("yyyy-MM-dd");
	    SimpleDateFormat dateformatYear = new SimpleDateFormat("yyyy");
	    SimpleDateFormat dateformatMonth = new SimpleDateFormat("MM");
		String year = dateformatYear.format(date);
		String month = dateformatMonth.format(date);
		HashMap<String, String> Header = new HashMap<String, String>();
		
		Header.put("PM-Metrics", "energyCurrentDate");
		
		try {
			String url = "https://portfoliomanager.energystar.gov/ws/property/" + message.getInvocationProperty("PropertyID") + "/metrics?" +"year=" + 
						year + "&month=" + month + "&measurementSystem=EPA";
			response = RESTCaller.send(url, "GET", null, Header);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Document doc = null;
		Date metricDate = null;
		try {
			doc = XMLParser.StringToXML(response);
		} catch (ParserConfigurationException | IOException | SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		NodeList root = doc.getChildNodes();
		Node prop = XMLParser.getNode("propertyMetrics", root);
		NodeList propDetails = prop.getChildNodes();
		Node ecd = XMLParser.getNodeAttr("metric", "energyCurrentDate", propDetails);
		try {
			metricDate = dateformatMetric.parse(XMLParser.getNodeValue("value", ecd.getChildNodes()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		year = dateformatYear.format(metricDate);
		month = dateformatMonth.format(metricDate);
		Header.clear();
		
		
		// Desired metrics:set 1 
		Header.put("PM-Metrics", "propertyFloorAreaBuildingsAndParking,propertyFloorAreaParking,score,siteTotal,siteIntensity,siteIntensityWN,sourceTotal,sourceIntensity,sourceIntensityWN,totalGHGEmissions");
		
		try {
			String url = "https://portfoliomanager.energystar.gov/ws/property/" + message.getInvocationProperty("PropertyID") + "/metrics?" +"year=" + 
						year + "&month=" + month + "&measurementSystem=EPA";
			response = RESTCaller.send(url, "GET", null, Header);
			message.setInvocationProperty("metrics1", response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Desired metrics:set 2
		Header.clear();
		Header.put("PM-Metrics", "medianTotalGHGEmissions,propertyDataAdministrator,propertyDataAdministratorEmail,medianScore,medianSiteTotal,medianSiteIntensity,medianSourceTotal,medianSourceIntensity");
		try {
			String url = "https://portfoliomanager.energystar.gov/ws/property/" + message.getInvocationProperty("PropertyID") + "/metrics?" +"year=" + 
						year + "&month=" + month + "&measurementSystem=EPA";
			response2 = RESTCaller.send(url, "GET", null, Header);
			message.setInvocationProperty("metrics2", response2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return message;
	}
}