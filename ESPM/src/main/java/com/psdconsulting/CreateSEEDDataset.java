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


// Properties being pushed into the SEED Platform require a data set container

public class CreateSEEDDataset extends AbstractMessageTransformer{

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
    	String fileName =  message.getInvocationProperty("OutboundFileName");
    	
    	String[] fileParts = fileName.split("\\.");
    	String setName = fileParts[0];
		
		HashMap<String, String> Header = new HashMap<String, String>();
		Header.put("authorization", user + ":" + apikey );
		
		String payload = "{\"organization_id\":\"" + orgId + "\",\"name\":\"" + setName + "\"}";
		l.info("Creating Dataset with payload: " + payload);
		byte [] postData = payload.getBytes();
		
		String response = null;
				
		// First call SEED to create a dataset for the file
		
		try {
			response = RESTCaller.send(host + "/app/create_dataset/", "POST", postData, Header);
			l.info("Created a dataset in the SEED system called: " + setName);
		} catch (Exception e) {
			throw new TransformerException(CoreMessages.createStaticMessage("Failed to create dataSet within SEED"), this, e);
		}
		//message.setInvocationProperty("create_dataset_response", response);
		l.info("Trying to cast response as JSON: " + response);		
		JSONObject obj = null;
		try {
			obj = new JSONObject(response);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int importRecord = 0;
		try {
			importRecord = obj.getInt("id");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		message.setInvocationProperty("ImportRecord", importRecord);
		l.info("Import Record: " + importRecord);
		
//		String line = null;
//		StringBuilder  stringBuilder = new StringBuilder();
//		BufferedReader br;
//		try {
//			br = new BufferedReader(new FileReader(filePath));
//			while( ( line = br.readLine() ) != null ) {
//		        stringBuilder.append( line );
//		    }
//			br.close();
//			l.info("Writing File Contents to invocation property");
//			message.setInvocationProperty("FileContents", String.format(stringBuilder.toString()));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			l.error("Bad Things while fileReading");
//			e.printStackTrace();
//		}
		
		
		
		
		
	return message;
	}
}
