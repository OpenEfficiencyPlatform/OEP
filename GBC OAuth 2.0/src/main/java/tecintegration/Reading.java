package tecintegration;

import java.math.BigDecimal;
import java.util.Date;

public class Reading {

	protected Date startDate;
	protected Date endDate;
	protected BigDecimal consumption;
	protected BigDecimal cost;
	protected Boolean estimated;

	
	public Date getStartDate() {
		return startDate;
	}

	
	public void setStartDate(Date value) {
		this.startDate = value;
	}

	
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date value) {
		this.endDate = value;
	}

	public BigDecimal getConsumption() {
		return consumption;
	}

	public void setConsumption(BigDecimal value) {
		this.consumption = value;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal value) {
		this.cost = value;
	}

	public Boolean getEstimated() {
		return estimated;
	}

	public void setEstimated(Boolean value) {
		this.estimated = value;
	}

}
