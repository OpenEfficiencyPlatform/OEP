package com.psdconsulting;

import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.config.i18n.CoreMessages;
import org.mule.transformer.AbstractMessageTransformer;

// A step in the SEED property import which matches building in the data set to existing SEED buildings. 

public class StartBuildingMatching extends AbstractMessageTransformer{

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		
		Logger l = Logger.getRootLogger();
		Properties prop = new Properties();
    	try {
    		prop.load(getClass().getClassLoader().getResourceAsStream("seedpmintegration.properties"));
    	} catch (Exception e) {
    		throw new TransformerException(CoreMessages.createStaticMessage("Failed to load configuration properties file: "), this, e);
    	}
    	String host = prop.getProperty("seed.host").toLowerCase();
    	String user = prop.getProperty("seed.userName").toString();
    	String apikey = prop.getProperty("seed.apiKey").toString();
    	String orgId = prop.getProperty("seed.org_id").toString();
    	int importFileID = message.getInvocationProperty("ImportFileID");
    	
		HashMap<String, String> Header = new HashMap<String, String>();
		Header.put("authorization", user + ":" + apikey );
		String payload = "{\"organization_id\":\"" + orgId + "\",\"file_id\":\"" + importFileID + "\"}";
		byte[] postData = payload.getBytes();
		String response = null;
		try {
			response = RESTCaller.send(host + "/app/remap_buildings/", "POST", postData, Header);
		l.info("Notified SEED that the background column mapping process for " + importFileID + " is ready to process");
			
			payload = "{\"organization_id\":\"" + orgId + "\",\"file_id\":\"" + importFileID + "\"}";
			postData = payload.getBytes();
			
			response = RESTCaller.send(host + "/app/start_system_matching/", "POST", postData, Header);
			l.info("Notified SEED that the background system matching process for " + importFileID + " is ready to process");
			
		} catch (Exception e) {
			throw new TransformerException(CoreMessages.createStaticMessage("Failed to start column mapping / system matching within SEED for import file: " + importFileID ), this, e);
		}
		l.info("Save Raw Data response: " + response);
		JSONObject obj = new JSONObject(response);
		String progressKey = obj.getString("progress_key");
		message.setInvocationProperty("saveProgressKey", progressKey);
		
		return message;
	}

}
