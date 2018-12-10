package tecintegration.transformers;

import java.util.Properties;
import java.util.UUID;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.config.i18n.CoreMessages;
import org.mule.transformer.AbstractMessageTransformer;

public class AssembleReturnURL extends AbstractMessageTransformer{

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
		
		String urlBase = prop.getProperty("sce.scopeSelection");
		String cisrId = message.getInvocationProperty("cisrId");
		String scopeSelection = message.getInvocationProperty("scope");
		String trueScope = "";
		try{
		String[] scopes = scopeSelection.split("#");
		trueScope = scopes[0];
		}catch(Exception e)
		{
			trueScope = scopeSelection;
			logger.info(scopeSelection);
		}
		
		UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
		
		String url = urlBase + "&redirect_uri=https://localhost/Authenticate/OAuthCallBack&response_type=code&scope="
				+ trueScope + "&state=" + randomUUIDString + "&cisrId=" + cisrId;
		
		
		message.setInvocationProperty("RedirectURL", url, MULE_MESSAGE_DATA_TYPE);
		return url;
	}

}
