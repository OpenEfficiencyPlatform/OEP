package com.psdconsulting;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.api.transport.PropertyScope;
import org.mule.config.i18n.CoreMessages;
import org.mule.transformer.AbstractMessageTransformer;



public class ProcessProject extends AbstractMessageTransformer{
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		
		Properties prop = new Properties();
    	try {
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			prop.load(cl.getResourceAsStream("edapt-to-salesforce.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	LinkedHashMap<String, Object> projectMap = new LinkedHashMap<String, Object>();
    try{
    	projectMap = (LinkedHashMap<String, Object>) message.getPayload();
    }
    catch(Exception e){
    	Logger.getRootLogger().info(message.getPayload());
    	return null;
    }
    @SuppressWarnings("unchecked")
	HashMap<String, String> project = (HashMap<String, String>) projectMap.get("project");
    
    Logger.getRootLogger().info("Getting Data for project: " + project.get("edapt_id"));
	String response;
	try {

		
	    String fullUrlPath = prop.getProperty("BaseURL").toString() + prop.getProperty("Project").toString() + "/" + project.get("edapt_id");
	    Logger.getRootLogger().info(fullUrlPath);
	    
    	HashMap<String, String> CookieHeader = new HashMap<String, String>();
    	CookieHeader.put("Cookie", message.getProperty("SessionCookie", PropertyScope.SESSION));
    	CookieHeader.put("X-CSRF-Token", message.getProperty("AuthToken", PropertyScope.SESSION));
	    
    	response = RESTCaller.send(fullUrlPath, "GET", null, CookieHeader);
		Logger.getRootLogger().info(response);
	} catch (Exception e) {
		throw new TransformerException(CoreMessages.createStaticMessage("Failed to retrieve pending projects"), this, e);
	}
	
	return response;
	}
}


