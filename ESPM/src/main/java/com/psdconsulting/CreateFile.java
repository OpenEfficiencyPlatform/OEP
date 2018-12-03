package com.psdconsulting;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

// Create the CSV container

public class CreateFile extends AbstractMessageTransformer{

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {

		Document xmlDoc = (Document) message.getPayload();
		Node root = XMLParser.getNode("pendingList", xmlDoc.getChildNodes());
		Node property = XMLParser.getNode("property", root.getChildNodes()); 
		
		if (property == null)
			return message;
						
		Properties prop = new Properties();
		String pmAcctNum;
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream("seedpmintegration.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pmAcctNum = prop.getProperty("pm.account");
		
	Date date = Calendar.getInstance().getTime();
    SimpleDateFormat dateformat = new SimpleDateFormat("MMddyy_HHmm");
    String fileName = "PM_" + pmAcctNum + "_" + dateformat.format(date) + ".csv." + (System.currentTimeMillis() / 1000L);
	String filePath = "/tmp/" + fileName;
	
	
	// Hard-coded headers
	try{
		PrintWriter dos = new PrintWriter(filePath, "UTF-8");
		dos.print("\"name\",\"primaryFunction\",\"yearBuilt\",\"numberOfBuildings\",\"propertyNotes\",\"address1\",\"address2\",\"city\",\"state\",\"postalCode\",\"createdDate\",\"propertyId\",\"" +
				"propGrossFloorArea\",\"propertyFloorAreaBuildingsAndParking\",\"propertyFloorAreaParking\",\"");
		dos.print("score\",\"siteTotal\",\"siteIntensity\",\"siteIntensityWN\",\"sourceTotal\",\"sourceIntensity\",\"sourceIntensityWN\",\"totalGHGEmissions\",\"medianTotalGHGEmissions\",\"" +
				"propertyDataAdministrator\",\"propertyDataAdministratorEmail\",\"medianScore\",\"medianSiteTotal\",\"medianSiteIntensity\",\"medianSourceTotal\",\"medianSourceIntensity\"");
		dos.println();
		dos.close();
		dos.flush();
	}
	catch (IOException e){}
	
	message.setInvocationProperty("OutboundFileName", fileName);
	message.setInvocationProperty("OutboundFilePath", filePath);
	

	
	return message;
	}
}

	


