package tecintegration.transformers;

import java.util.ArrayList;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

public class GetResourcesFromNotification extends AbstractMessageTransformer{

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
	
		Object notification = message.getPayload();

		ArrayList<Object> notifications = (ArrayList<Object>) notification;
		return message;
	
	}

}
