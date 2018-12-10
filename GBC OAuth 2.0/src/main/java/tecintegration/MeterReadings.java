package tecintegration;

import java.util.ArrayList;
import java.util.List;

public class MeterReadings {

	protected List<Reading> readings;

	public List<Reading> getReadings() {
		if (readings == null) {
			readings = new ArrayList<Reading>();
		}
		return this.readings;
	}

	public void setReading(List<Reading> readings) {
		this.readings = readings;
	}

}
