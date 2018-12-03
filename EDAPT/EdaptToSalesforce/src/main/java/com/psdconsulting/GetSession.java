package com.psdconsulting;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.config.i18n.CoreMessages;
import org.mule.transformer.AbstractMessageTransformer;



public class GetSession extends AbstractMessageTransformer{
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
		
	
    Logger.getRootLogger().info("Getting Session Cookie");
	String response;
	try {

	    String fullUrlPath = prop.getProperty("BaseURL").toString() + prop.getProperty("Login").toString();
	    Logger.getRootLogger().info(fullUrlPath);
	    
    	String data = "{\"username\": \"" + prop.getProperty("username").toString() + "\",\"password\":\"" +prop.getProperty("password").toString() + "\"}";
		//Log.getRootLogger().info(data);
    	byte[] postData = data.getBytes();
		response = RESTCaller.send(fullUrlPath, "POST", postData, null);
		Logger.getRootLogger().info(response);
	} catch (Exception e) {
		throw new TransformerException(CoreMessages.createStaticMessage("Failed to retrieve session cookie"), this, e);
	}
	
	return response;
	}
}


