
def property = flowVars["PropertyDetails"].state;
def cycle = flowVars["PropertyDetails"].cycle;
def propData = property.extra_data;


message.setInvocationProperty('SFPremiseId', propData["Benchmark Salesforce ID"]);

Benchmark = [
	Id: propData["Benchmark Salesforce ID"],

	// Custom fields per Org
	oei__Contact_Name__c: flowVars["ContactResults"],
	oei__Diff_from_National_Median_Site_EUI__c: propData["% Difference from National Median Site EUI"],
	oei__Diff_than_National_Median_Source_EUI__c: propData["% Difference from National Median Source EUI"],
	oei__ENERGY_STAR_Score__c: propData["ENERGY STAR Score"],
	oei__Portfolio_Manger_Property_ID__c:property.pm_property_id,
	oei__Primary_Property_Type_PM_Calculated__c: propData["Primary Property Type - Portfolio Manager-Calculated"].toString(),
	oei__Property_GFA_Calculated_Build_Park__c: propData["Property GFA - Calculated (Buildings and Parking) (ft2)"],
	oei__Property_GFA_Calculated_buildings__c: propData["Property GFA - Calculated (Buildings) (ft2)"],
	oei__SEED_Labels__c: flowVars["labelNameString"],
	oei__Cycle__c: cycle.name,
	oei__Site_EUI_kBtu_ft2__c: propData["Site EUI (kBtu/ft2)"],
	oei__Source_EUI_kBtu_ft2__c: propData["Source EUI (kBtu/ft2)"],
	oei__Status__c: flowVars["IsInViolation"] ? flowVars["ViolationStatus"] : (flowVars["labelNameString"].contains(flowVars["CompliedStatus"]) ? flowVars["CompliedStatus"] : null),
	oei__Total_GHG_Emissions_Intensity_kgCO2e_ft__c: propData["Total GHG Emissions Intensity (kgCO2e/ft2)"],
	oei__Total_GHG_Emissions_Metric_Tons_CO2e__c: propData["Total GHG Emissions (Metric Tons CO2e)"],
	oei__Weather_Normalized_Site_Electricity_Int__c: propData["Weather Normalized Site Electricity Intensity (kWh/ft2)"],
	oei__Weather_Normalized_Site_EUI_kBtu_ft2__c: propData["Weather Normalized Site EUI (kBtu/ft2)"],
	oei__Weather_Normalized_Site_Natural_Gas_Int__c: propData["Weather Normalized Site Natural Gas Intensity (therms/ft2)"],
	oei__Weather_Normalized_Source_EUI_kBtu_ft2__c: propData["Weather Normalized Source EUI (kBtu/ft2)"]

]
return [Benchmark]
