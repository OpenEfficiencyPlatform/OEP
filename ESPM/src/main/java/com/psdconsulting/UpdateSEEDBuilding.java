package com.psdconsulting;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.config.i18n.CoreMessages;
import org.mule.transformer.AbstractMessageTransformer;



// Properties being pushed into the SEED Platform require a data set container

public class UpdateSEEDBuilding extends AbstractMessageTransformer{

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
    	LinkedHashMap<String, String> building =  message.getInvocationProperty("SeedBuilding");
    	l.info(building);
    	
    	String taxLotID =  message.getInvocationProperty("TaxLotID");
    	
		HashMap<String, String> Header = new HashMap<String, String>();
		Header.put("authorization", user + ":" + apikey );
		building.put("tax_lot_id", taxLotID);
		
		l.info("Tax Lot Id:" + taxLotID);
		
		String jsonString = new JSONObject(building).toString();
		
		
		
		String payload = "{\"organization_id\":\"" + orgId + "\",\"building\":" +  jsonString + "}";
		
		l.info("payload: " + payload);
		l.info("Updating SEED building with payload: " + payload);
		byte [] postData = payload.getBytes();
		
		String response = null;
				
		try {
			response = RESTCaller.send(host + "/app/update_building/", "POST", postData, Header);
		} catch (Exception e) {
			throw new TransformerException(CoreMessages.createStaticMessage("Failed update building within SEED"), this, e);
		}

		l.info(response);
		return response;
	
	}
}
