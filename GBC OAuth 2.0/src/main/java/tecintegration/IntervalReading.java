package tecintegration;

import java.math.BigDecimal;
import javax.xml.bind.annotation.*;

@XmlType(propOrder={"cost", "timePeriod", "value"}, namespace="http://naesb.org/espi")
public class IntervalReading {
	
	protected BigDecimal cost;
	protected TimePeriod timePeriod;
	protected BigDecimal value;
	
	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal value) {
		this.cost = value;
	}
	
	@XmlElement(name="timePeriod")
	public TimePeriod getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(TimePeriod value) {
		this.timePeriod = value;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	

}
