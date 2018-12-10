package tecintegration.transformers;

import java.io.IOException;
import java.util.Properties;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

public class AuthenticateRequest extends AbstractMessageTransformer {
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		

		Properties prop = new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream("GBCintegration.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MeterDetails request = (MeterDetails) message.getPayload();
		String AuthID = request.getAuthorizationID();
		
		String Authenticated = "false";
		
		if (AuthID.equals(prop.getProperty("AuthKey")))
			Authenticated = "true";
		
		message.setInvocationProperty("IsAuthorized", Authenticated);
				
		return message;
		
	}
}
