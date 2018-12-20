package tecintegration;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"duration", "start"})

public class TimePeriod {
	
	protected long start;
	protected long duration;
		
	public long getStart() {
		return start;
	}
	
	public void setStart(long value) {
		this.start = value;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long value) {
		this.duration = value;
	}

}
