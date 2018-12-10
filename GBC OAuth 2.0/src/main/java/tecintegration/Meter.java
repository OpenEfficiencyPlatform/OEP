package tecintegration;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class Meter {

	protected String meterID;
	protected String meterName;
	protected String utilityID;
	protected String utilityName;
	protected String accountNumber;
	protected String energyType;
	protected String energyUnit;
	protected Boolean annualBilling;
	protected List<Reading> readings;
	protected Double annualizedUsage;
	protected BigDecimal annualCost;
	protected Date annualEndDate;
	protected BigDecimal unitCost;
	protected Boolean estimateConsumption;
	protected String cisrID;

	public String getMeterID() {
		return meterID;
	}

	public void setMeterID(String value) {
		this.meterID = value;
	}

	public String getMeterName() {
		return meterName;
	}

	public void setMeterName(String value) {
		this.meterName = value;
	}

	public String getUtilityID() {
		return utilityID;
	}

	public void setUtilityID(String value) {
		this.utilityID = value;
	}

	public String getUtilityName() {
		return utilityName;
	}

	public void setUtilityName(String value) {
		this.utilityName = value;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String value) {
		this.accountNumber = value;
	}

	public String getEnergyType() {
		return energyType;
	}

	public void setEnergyType(String value) {
		this.energyType = value;
	}

	public String getEnergyUnit() {
		return energyUnit;
	}

	public void setEnergyUnit(String value) {
		this.energyUnit = value;
	}

	public Boolean getAnnualBilling() {
		return annualBilling;
	}

	public void setAnnualBilling(Boolean value) {
		this.annualBilling = value;
	}

	public List<Reading> getReadings() {
		return readings;
	}

	public void setReadings(List<Reading> readings) {
		this.readings = readings;
	}

	public Double getAnnualizedUsage() {
		return annualizedUsage;
	}

	public void setAnnualizedUsage(Double value) {
		this.annualizedUsage = value;
	}

	public BigDecimal getAnnualCost() {
		return annualCost;
	}

	public void setAnnualCost(BigDecimal value) {
		this.annualCost = value;
	}

	public Date getAnnualEndDate() {
		return annualEndDate;
	}

	public void setAnnualEndDate(Date value) {
		this.annualEndDate = value;
	}

	public BigDecimal getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(BigDecimal value) {
		this.unitCost = value;
	}

	public Boolean getEstimateConsumption() {
		return estimateConsumption;
	}

	public void setEstimateConsumption(Boolean value) {
		this.estimateConsumption = value;
	}
	
	public String getcsirID() {
		return cisrID;
	}

	public void setcisrID(String value) {
		this.cisrID = value;
	}

}
