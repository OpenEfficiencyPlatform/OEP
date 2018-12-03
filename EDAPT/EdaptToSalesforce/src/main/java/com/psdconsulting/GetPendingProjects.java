package com.psdconsulting;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.api.transport.PropertyScope;
import org.mule.config.i18n.CoreMessages;
import org.mule.transformer.AbstractMessageTransformer;



public class GetPendingProjects extends AbstractMessageTransformer{
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
		
	
    Logger.getRootLogger().info("Getting List of Pending Projects");
	String response;
	try {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DATE, -1);
		String date = dateFormat.format(yesterday.getTime());
	    String fullUrlPath = prop.getProperty("BaseURL").toString() + prop.getProperty("Activity").toString() + "/" + date;
		
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


