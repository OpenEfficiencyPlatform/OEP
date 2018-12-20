package tecintegration.transformers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusMessage", propOrder = {
    "type",
    "message"
})
public class StatusMessage {

	@XmlElement(name = "Type")
    protected String type;
    @XmlElement(name = "Message")
    protected String message;
   
    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String value) {
        this.message = value;
    }
}
