package tecintegration.transformers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Result", propOrder = { "statusMessage" })
public class UpdateResult {

	@XmlElement(name = "StatusMessage")
	protected StatusMessage statusMessage;

    public StatusMessage getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(StatusMessage value) {
        this.statusMessage = value;
    }
    
}
