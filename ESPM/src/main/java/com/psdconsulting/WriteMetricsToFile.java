package com.psdconsulting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.config.i18n.CoreMessages;
import org.mule.transformer.AbstractMessageTransformer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// After retrieving the PM Metrics for a property, write the response to the flat file.

public class WriteMetricsToFile extends AbstractMessageTransformer{

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
	
	
	String filePath = message.getInvocationProperty("OutboundFilePath");
	String propID = message.getInvocationProperty("PropertyID");
	String preDoc = message.getInvocationProperty("metrics1");
	
	ArrayList<LinkedHashMap<String, Object>> jobs = message.getInvocationProperty("SFJob");
	LinkedHashMap<String, Object> sfjob = jobs.get(0);
	
		
	Document doc = null;
	Document doc2 = null;

	try {
		doc = XMLParser.StringToXML(preDoc);//score, propertyFloorAreaBuildingsAndParking,propertyFloorAreaParking, siteTotal, siteIntensity, siteIntensityWN, sourceTotal, sourceIntensity, sourceIntensityWN, totalGHGEmissions
		String preDoc2 = message.getInvocationProperty("metrics2"); //medianTotalGHGEmissions, propertyDataAdministrator, propertyDataAdministratorEmail, medianScore, medianSiteTotal, medianSiteIntensity, medianSourceTotal, medianSourceIntensity
		doc2 = XMLParser.StringToXML(preDoc2);
	} catch (ParserConfigurationException | IOException | SAXException e) {
		throw new TransformerException(CoreMessages.createStaticMessage("Failed to parse string to XML"), this, e);
	}
	
	try {
		FileWriter fileWriter = new FileWriter(filePath, true);

        // Always wrap FileWriter in BufferedWriter.
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                
        // Get the document's root XML node
        NodeList root = doc.getChildNodes();
        NodeList root2 = doc2.getChildNodes();
        
        // Navigate down the hierarchy to get to the property node
        Node prop = XMLParser.getNode("propertyMetrics", root);
        NodeList propDetails = prop.getChildNodes();
        
        Node prop2 = XMLParser.getNode("propertyMetrics", root2);
        NodeList prop2Details = prop2.getChildNodes();
        
        // Write 1st set of metrics
        Node pfABP = XMLParser.getNodeAttr("metric", "propertyFloorAreaBuildingsAndParking", propDetails);
       	bufferedWriter.write(XMLParser.getNodeValue("value", pfABP.getChildNodes())+ "\",\"");
        
        Node pfAp = XMLParser.getNodeAttr("metric", "propertyFloorAreaParking", propDetails);
       	bufferedWriter.write(XMLParser.getNodeValue("value", pfAp.getChildNodes())+ "\",\"");
        
        Node scoreMetric = XMLParser.getNodeAttr("metric", "score", propDetails);
        bufferedWriter.write(XMLParser.getNodeValue("value", scoreMetric.getChildNodes())+ "\",\"");
        sfjob.put("PM_Score__c", XMLParser.getNodeValue("value", scoreMetric.getChildNodes()));
        
        Node st = XMLParser.getNodeAttr("metric", "siteTotal", propDetails);
        bufferedWriter.write(XMLParser.getNodeValue("value", st.getChildNodes())+ "\",\"");
        
        Node si = XMLParser.getNodeAttr("metric", "siteIntensity", propDetails);
        bufferedWriter.write(XMLParser.getNodeValue("value", si.getChildNodes())+ "\",\"");
        sfjob.put("SiteEUI__c", XMLParser.getNodeValue("value", si.getChildNodes()));
        
        Node siWN = XMLParser.getNodeAttr("metric", "siteIntensityWN", propDetails);
        bufferedWriter.write(XMLParser.getNodeValue("value", siWN.getChildNodes())+ "\",\"");
        
        Node srct = XMLParser.getNodeAttr("metric", "sourceTotal", propDetails);
        bufferedWriter.write(XMLParser.getNodeValue("value", srct.getChildNodes())+ "\",\"");
        
        Node srci = XMLParser.getNodeAttr("metric", "sourceIntensity", propDetails);
        bufferedWriter.write(XMLParser.getNodeValue("value", srci.getChildNodes())+ "\",\"");
        
        Node srciWN = XMLParser.getNodeAttr("metric", "sourceIntensityWN", propDetails);
        bufferedWriter.write(XMLParser.getNodeValue("value", srciWN.getChildNodes())+ "\",\"");
        
        Node tGHG = XMLParser.getNodeAttr("metric", "totalGHGEmissions", propDetails);
        bufferedWriter.write(XMLParser.getNodeValue("value", tGHG.getChildNodes())+ "\",\"");
        sfjob.put("TotalGHG__c", XMLParser.getNodeValue("value", tGHG.getChildNodes()));
//        
//      write 2nd set of metric
        Node mtGHG = XMLParser.getNodeAttr("metric", "medianTotalGHGEmissions", prop2Details);
        bufferedWriter.write(XMLParser.getNodeValue("value", mtGHG.getChildNodes())+ "\",\"");

        Node pda = XMLParser.getNodeAttr("metric", "propertyDataAdministrator", prop2Details);
        bufferedWriter.write(XMLParser.getNodeValue("value", pda.getChildNodes())+ "\",\"");
        
        Node pdaE = XMLParser.getNodeAttr("metric", "propertyDataAdministratorEmail", prop2Details);
        bufferedWriter.write(XMLParser.getNodeValue("value", pdaE.getChildNodes())+ "\",\"");
        
        Node ms = XMLParser.getNodeAttr("metric", "medianScore", prop2Details);
        bufferedWriter.write(XMLParser.getNodeValue("value", ms.getChildNodes())+ "\",\"");
        
        Node mst = XMLParser.getNodeAttr("metric", "medianSiteTotal", prop2Details);
        bufferedWriter.write(XMLParser.getNodeValue("value", mst.getChildNodes())+ "\",\"");
        
        Node msi = XMLParser.getNodeAttr("metric", "medianSiteIntensity", prop2Details);
        bufferedWriter.write(XMLParser.getNodeValue("value", msi.getChildNodes())+ "\",\"");
        
        Node msTo = XMLParser.getNodeAttr("metric", "medianSourceTotal", prop2Details);
        bufferedWriter.write(XMLParser.getNodeValue("value", msTo.getChildNodes())+ "\",\"");
        
        Node msrci = XMLParser.getNodeAttr("metric", "medianSourceIntensity", prop2Details);
        bufferedWriter.write(XMLParser.getNodeValue("value", msrci.getChildNodes())+ "\"");
       
        bufferedWriter.newLine();
        
        // Always close files.
        bufferedWriter.close();
        fileWriter.close();
        
        Logger.getRootLogger().info("Writing Metrics to: " + filePath + " for: " + propID );
        jobs.clear();
        jobs.add(sfjob);
        message.setInvocationProperty("SFJob", jobs);
        
    }
    catch(IOException ex) {
		throw new TransformerException(CoreMessages.createStaticMessage("Failed to write PM metrics to file"), this, ex);
    }
	
	return message;
	}
}

	


