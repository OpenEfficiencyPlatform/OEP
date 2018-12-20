package tecintegration.transformers;
import java.util.HashMap;
import java.util.Properties;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.config.i18n.CoreMessages;
import org.mule.transformer.AbstractMessageTransformer;

public class AssembleResourceRequest extends AbstractMessageTransformer{

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		
		Properties prop = new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream(
					"GBCintegration.properties"));
		} catch (Exception e) {
			throw new TransformerException(
					CoreMessages
							.createStaticMessage("Failed to load configuration properties file: "),
					this, e);
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Author", "https://localhost/Authenticate/OAuthCallBack");
		map.put("grant_type", "client_credentials");
		map.put("client_id", prop.getProperty("sce.client.id"));
		map.put("client_secret",prop.getProperty("sce.shared.secret"));
		map.put("scope", message.getInvocationProperty("scope").toString());
		
		return map;
	}
	
}

