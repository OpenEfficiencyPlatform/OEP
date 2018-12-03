package com.psdconsulting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//After retrieving the PM Property details, write the response to the flat file.

public class WritePropertyDetailsToFile extends AbstractMessageTransformer{

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
	
	
	String filePath = message.getInvocationProperty("OutboundFilePath");
	String propID = message.getInvocationProperty("PropertyID");
	Document doc = (Document) message.getPayload();
	ArrayList<LinkedHashMap<String, Object>> jobs = new ArrayList<LinkedHashMap<String, Object>>();
    LinkedHashMap<String, Object> sfjob = new LinkedHashMap<String, Object>();
	
	try {
	FileWriter fileWriter = new FileWriter(filePath, true);

        // Always wrap FileWriter in BufferedWriter.
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                
        // Get the document's root XML node
        NodeList root = doc.getChildNodes();
        
        // Navigate down the hierarchy to get to the property node
        Node prop = XMLParser.getNode("Property", root);
        NodeList propDetails = prop.getChildNodes();
        
        bufferedWriter.write("\"");
        bufferedWriter.write(XMLParser.getNodeValue("name", propDetails) + "\",\"");
        sfjob.put("psdconsulting__ExternalID__c", propID);
        sfjob.put("Name", XMLParser.getNodeValue("name", propDetails));
        
        bufferedWriter.write(XMLParser.getNodeValue("primaryFunction", propDetails) + "\",\"");
        sfjob.put("psdconsulting__SubType__c", XMLParser.getNodeValue("primaryFunction", propDetails));
        
        bufferedWriter.write(XMLParser.getNodeValue("yearBuilt", propDetails) + "\",\"");
        sfjob.put("psdconsulting__YearBuilt__c", XMLParser.getNodeValue("yearBuilt", propDetails));
        bufferedWriter.write(XMLParser.getNodeValue("numberOfBuildings", propDetails) + "\",,\"");
        
        Node address = XMLParser.getNode("address", propDetails );
        bufferedWriter.write(XMLParser.getNodeAttr("address1", address) + "\",,\"");
        sfjob.put("psdconsulting__Street__c", XMLParser.getNodeAttr("address1", address));
        bufferedWriter.write(XMLParser.getNodeAttr("city", address) + "\",\"");
        sfjob.put("psdconsulting__City__c", XMLParser.getNodeAttr("city", address));
        bufferedWriter.write(XMLParser.getNodeAttr("state", address) + "\",\"");
        sfjob.put("psdconsulting__State__c", XMLParser.getNodeAttr("state", address));
        bufferedWriter.write(XMLParser.getNodeAttr("postalCode", address) + "\",\"");
        sfjob.put("psdconsulting__Zip__c", XMLParser.getNodeAttr("postalCode", address));
        
        Node audit = XMLParser.getNode("audit", propDetails);
        NodeList auditDetails = audit.getChildNodes();
        bufferedWriter.write(XMLParser.getNodeValue("createdDate", auditDetails) + "\",\"");
        bufferedWriter.write(propID + "\",\"");
                
        Node gfa = XMLParser.getNode("grossFloorArea", prop.getChildNodes());
        bufferedWriter.write(XMLParser.getNodeValue("value", gfa.getChildNodes())+ "\",\"");
        sfjob.put("psdconsulting__GrossFloorArea__c", XMLParser.getNodeValue("value", gfa.getChildNodes()));
        jobs.add(sfjob);
        message.setInvocationProperty("SFJob", jobs);
        // Always close files.
        bufferedWriter.close();
        fileWriter.close();
        
        Logger.getRootLogger().info("Writing Property Details to: " + filePath + " for: " + propID );
    }
    catch(IOException ex) {
        System.out.println(
            "Error writing to file '"
            + filePath + "'");
        // Or we could just do this:
        // ex.printStackTrace();
    }
	return message;
	}
}

	


