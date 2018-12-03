package com.psdconsulting;

import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.config.i18n.CoreMessages;
import org.mule.transformer.AbstractMessageTransformer;


// For several SEED requests a waiting period requires checking the "processing status" of the request.

public class CheckProgress extends AbstractMessageTransformer{

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
    	String saveProgressKey = message.getInvocationProperty("saveProgressKey");
    	int importFileID = message.getInvocationProperty("ImportFileID");
    	HashMap<String, String> Header = new HashMap<String, String>();
		Header.put("authorization", user + ":" + apikey );
    	
    	String payload = "{\"progress_key\":\"" + saveProgressKey + "\"}";
    	String response = null;
    	
    	try {
			response = RESTCaller.send(host + "/app/progress/", "PUT", payload.getBytes(), Header);
			l.info("Checking the status of saving the file " + importFileID);
		} catch (Exception e) {
			throw new TransformerException(CoreMessages.createStaticMessage("Failed to retrieve the status of saving the file: " + importFileID ), this, e);
		}
		
    	JSONObject obj = null;
		try {
			obj = new JSONObject(response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String statusProgress = null;
		try {
			statusProgress = obj.getString("status");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		l.info("Progress response: " + response);
		message.setInvocationProperty("progressStatus", statusProgress);
		
		return payload;
		}
}
