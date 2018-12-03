package com.psdconsulting;

import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.config.i18n.CoreMessages;
import org.mule.transformer.AbstractMessageTransformer;


// Properties being pushed into the SEED Platform require a data set container

public class FindSEEDBuilding extends AbstractMessageTransformer{

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
    	String address =  message.getInvocationProperty("PropertyAddress");
    	HashMap<String, String> Header = new HashMap<String, String>();
		Header.put("authorization", user + ":" + apikey );
		
		String payload = "{\"q\":\"\", \"number_per_page\":1, \"page\":1, \"filter_params\":{\"address_line_1\":\"" + address +  "\"}}";
					
		l.info("Searching for SEED building with payload: " + payload);
		byte [] postData = payload.getBytes();
		
		String response = null;
				
		try {
			response = RESTCaller.send(host + "/app/search_buildings/", "POST", postData, Header);
			l.info("Search results:" + response);
		} catch (Exception e) {
			throw new TransformerException(CoreMessages.createStaticMessage("Failed to create dataSet within SEED"), this, e);
		}

		return response;

	}
}
