package com.psdconsulting;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.api.transport.PropertyScope;
import org.mule.config.i18n.CoreMessages;
import org.mule.transformer.AbstractMessageTransformer;



public class SetAuthToken extends AbstractMessageTransformer{
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
		
	
    Logger.getRootLogger().info("Getting Authorization Token");
	String response;
	try {

	    String fullUrlPath = prop.getProperty("BaseURL").toString() + prop.getProperty("TokenPath").toString();
	    Logger.getRootLogger().info(fullUrlPath);
	    
    	HashMap<String, String> CookieHeader = new HashMap<String, String>();
    	CookieHeader.put("Cookie", message.getProperty("SessionCookie", PropertyScope.SESSION));
    	response = RESTCaller.send(fullUrlPath, "POST", null, CookieHeader);
    	message.setProperty("AuthToken", response, PropertyScope.SESSION);
		Logger.getRootLogger().info(response);
	} catch (Exception e) {
		throw new TransformerException(CoreMessages.createStaticMessage("Failed to retrieve token"), this, e);
	}
	
	return message;
	}
}


