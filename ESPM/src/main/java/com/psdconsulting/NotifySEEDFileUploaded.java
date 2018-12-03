package com.psdconsulting;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.config.i18n.CoreMessages;
import org.mule.transformer.AbstractMessageTransformer;

// After pushing the .csv to the Amazon S3 account, give the SEED API the pertinent details 

public class NotifySEEDFileUploaded extends AbstractMessageTransformer{

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
    	String fileName = message.getInvocationProperty("OutboundFileName");
    	String filePath =  message.getInvocationProperty("OutboundFilePath");
    	int importRecord = message.getInvocationProperty("ImportRecord");
    	String response = null;
		
		HashMap<String, String> Header = new HashMap<String, String>();
		Header.put("authorization", user + ":" + apikey );
		
		String payload = filePath + fileName + String.valueOf(importRecord);
		byte[] postData = payload.getBytes();
		
		try {
			response = RESTCaller.send(host + "/data/upload/", "POST", postData, Header);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject obj = null;
		try {
			obj = new JSONObject(response);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int importFileID = 0;
		try {
			importFileID = obj.getInt("import_file_id");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		
		message.setInvocationProperty("ImportFileID", importFileID);
		
		payload = "{\"organization_id\":\"" + orgId + "\",\"file_id\":\"" + importFileID + "\"}";
		postData = payload.getBytes();
		
		response = null;
				
		// Next save the raw data from the imported file to the SEED DB
		
		try {
			response = RESTCaller.send(host + "/app/save_raw_data/","POST", postData, Header);
			l.info("Notified SEED that the import file " + importFileID + " is ready to process");
		} catch (Exception e) {
			throw new TransformerException(CoreMessages.createStaticMessage("Failed to save raw data within SEED for import file: " + importFileID ), this, e);
		}
		l.info("Save Raw Data response: " + response);
		try {
			obj = new JSONObject(response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String progressKey = null;
		try {
			progressKey = obj.getString("progress_key");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		message.setInvocationProperty("saveProgressKey", progressKey);
		
	return response;
	}
}

