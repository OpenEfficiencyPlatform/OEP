package tecintegration.transformers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MeterDetails", propOrder = { "authID",
		"meterID" })
public class MeterDetails {
	
	@XmlElement(name = "AuthID", required = true)
    protected String authID;
	
	@XmlElement(name = "MeterID", required = true)
    protected String meterID;
	
	public String getMeterID() {
        return meterID;
    }
    public void setMeterID(String value) {
        this.meterID = value;
    }
    public String getAuthorizationID() {
        return authID;
    }
    public void setAuthorizationID(String value) {
        this.authID = value;
    }

}
