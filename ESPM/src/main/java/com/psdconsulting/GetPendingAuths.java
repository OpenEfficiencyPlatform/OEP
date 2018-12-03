package com.psdconsulting;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.config.i18n.CoreMessages;
import org.mule.transformer.AbstractMessageTransformer;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


//Properties shared to the active account will get presented as "Pending Shares".

public class GetPendingAuths extends AbstractMessageTransformer{
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
	
	String response;
	try {
		response = RESTCaller.send("https://portfoliomanager.energystar.gov/ws/share/property/pending/list", "GET", null, null);
	} catch (Exception e) {
		throw new TransformerException(CoreMessages.createStaticMessage("Failed to send REST request to PM"), this, e);
	}
	
	response=response.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();
	
	Document doc = null;
	try {
		doc = XMLParser.StringToXML(response);
	} catch (ParserConfigurationException | IOException | SAXException e) {
		throw new TransformerException(CoreMessages.createStaticMessage("Failed to parse string to XML"), this, e);
	}
	
	return doc;
	}
}
