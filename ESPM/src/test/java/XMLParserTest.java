import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;


import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.psdconsulting.XMLParser;


public class XMLParserTest {
	
	@Test
	public void DocumentGetsCreatedFromString()
	{
		
		String xmlString = "<foo>innerfoo</foo>";
		Document myNewXML = null;
		try {
			myNewXML = XMLParser.StringToXML(xmlString);
		} catch (ParserConfigurationException | IOException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Node n = myNewXML.getFirstChild();
		NodeList nl = n.getChildNodes();
		Node an;
		String wat = null;
		
		for (int i=0; i < nl.getLength(); i++) {
		    an = nl.item(i);
		    wat = an.getTextContent();
		}
		
		Assert.assertEquals("innerfoo", wat);
    		
	}

}
