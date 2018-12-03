package com.psdconsulting;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.config.i18n.CoreMessages;
import org.mule.transformer.AbstractMessageTransformer;
import org.xml.sax.SAXException;

// Retrieve Property details to include in the flattened row of data going to SEED

public class GetPropertyDetails extends AbstractMessageTransformer{
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
	
	
		String response = "";
		try {
			response = RESTCaller.send("https://portfoliomanager.energystar.gov/ws/property/" + message.getInvocationProperty("PropertyID"), "GET", null, null);
		} catch (Exception e) {
			throw new TransformerException(CoreMessages.createStaticMessage("Failed to send REST request to PM"), this, e);
		}
		try {
			return XMLParser.StringToXML(response);
		} catch (ParserConfigurationException | IOException | SAXException e) {
			throw new TransformerException(CoreMessages.createStaticMessage("Failed to parse string to XML"), this, e);
		}
	
	}
}
