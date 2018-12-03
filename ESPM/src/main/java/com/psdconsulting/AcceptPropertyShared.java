package com.psdconsulting;


import org.apache.log4j.Logger;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

// "Pending Shares" need to be accepted by an account before any details are accessible

public class AcceptPropertyShared extends AbstractMessageTransformer{
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
	String propertyId = "";
	String response = null;
	try {
		String[] dumb = message.getPayloadAsString().trim().split(":");
		propertyId = dumb[1].replace("]","").trim();
		Logger.getRootLogger().info("Accepting Property Share for:" + propertyId );
		String accept = "<sharingResponse><action>Accept</action></sharingResponse>";
		byte [] postData = accept.getBytes();
		response = RESTCaller.send("https://portfoliomanager.energystar.gov/ws/share/property/" + propertyId, "POST", postData, null);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if (response.contains("Ok"))
		return propertyId;
	
	return response;
	}
}
