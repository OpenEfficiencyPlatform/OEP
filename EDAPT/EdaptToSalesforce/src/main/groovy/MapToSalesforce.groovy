import java.util.Date
import java.util.Calendar
import java.text.SimpleDateFormat

import javax.xml.namespace.QName;

import com.sforce.soap.partner.sobject.SObject;

projectDetails = payload.project;

OEP_EDAPT__Project__c = [
		OEP_EDAPT__External_ID__c: projectDetails.edapt_id,
		OEP_EDAPT__Project__c: projectDetails.utility_project_number,
		OEP_EDAPT__Opportunity__c: projectDetails.sfdc_opportunity_number,
		OEP_EDAPT__Project_Name__c: projectDetails.project_name,
		Name: projectDetails.project_name,
		OEP_EDAPT__Customer_Name__c: projectDetails.customer_name,
		OEP_EDAPT__Construction_Status__c: projectDetails.project_status,
		OEP_EDAPT__Last_Update_Date__c: getDateTime(projectDetails.last_update_date),
		OEP_EDAPT__Energy_Consultant__c: projectDetails.energy_consultant,
		OEP_EDAPT__Process__c: projectDetails.process,
		OEP_EDAPT__EEE_Audit__c: projectDetails.eee_audit,
		OEP_EDAPT__Occupancy_Classification__c: projectDetails.primary_building_type,
		OEP_EDAPT__Occupancy_Classification_Secondary__c: projectDetails.secondary_building_type,
		OEP_EDAPT__Assessment_Recognition__c: projectDetails.certification,
		OEP_EDAPT__Action_Category__c: projectDetails.new_addition_or_renovation,
		OEP_EDAPT__Utility_Gas_Customer__c: projectDetails.utility_gas_customer,
		OEP_EDAPT__Application_Approval_Date__c: getDate(projectDetails.application_approval_date),
		OEP_EDAPT__Intro_Meeting_Date__c: getDate(projectDetails.intro_meeting_date),
		OEP_EDAPT__Intro_Meeting_Year__c: projectDetails.intro_meeting_year,
		OEP_EDAPT__PEA_Report_Approved__c: getDate(projectDetails.pea_report_approved),
		OEP_EDAPT__FEA_Report_Approved__c: getDate(projectDetails.fea_report_approved),
		OEP_EDAPT__CD_Report_Approved__c: getDate(projectDetails.cd_report_approved),
		OEP_EDAPT__On_Site_Verification_1__c: getDate(projectDetails.on_site_verification_1),
		OEP_EDAPT__On_Site_Verification_2__c: getDate(projectDetails.on_site_verification_2),
		OEP_EDAPT__Install_Dates_of_Equipment_Monitoring__c: getDate(projectDetails.installation_dates_of_equipment_monitoring),
		OEP_EDAPT__Removal_Dates_of_Equipment_Monitoring__c: getDate(projectDetails.removal_dates_of_equipment_monitoring),
		OEP_EDAPT__MV_Report_Approved__c: getDate(projectDetails.mv_report_approved),
		OEP_EDAPT__Project_Completion_Date__c: getDate(projectDetails.project_completion_date),
		OEP_EDAPT__Project_Baseline__c: projectDetails.project_baseline,
		OEP_EDAPT__Intro_Electric_Demand_Savings_Estimate__c: projectDetails.intro_electricity_demand_savings_estimate,
		OEP_EDAPT__Intro_Electricity_Savings_Estimate__c: projectDetails.intro_electricity_consumption_savings_estimate,
		OEP_EDAPT__Intro_Natural_Gas_Savings_Estimate__c: projectDetails.intro_natural_gas_consumption_savings_estimate,
		
		OEP_EDAPT__Stage4_Energy_Cost_Baseline__c: projectDetails.stage4_energy_cost_baseline,
		OEP_EDAPT__Stage4_Energy_Cost__c: projectDetails.stage4_energy_cost,
		OEP_EDAPT__Stage4_Electricity_Demand_Baseline__c: projectDetails.stage4_electricity_demand_baseline,
		OEP_EDAPT__Stage4_Electricity_Demand__c: projectDetails.stage4_electricity_demand,
		OEP_EDAPT__Stage4_Electricity_Consumption_Baseline__c: projectDetails.stage4_electricity_consumption_baseline,
		OEP_EDAPT__Stage4_Electricity_Consumption__c: projectDetails.stage4_electricity_consumption,
		OEP_EDAPT__Stage4_Electricity_Consumption_Savings__c: projectDetails.stage4_electricity_consumption_savings,
		OEP_EDAPT__Stage4_Electricity_Demand_Savings__c: projectDetails.stage4_electricity_demand_savings,
		OEP_EDAPT__Stage4_Energy_Cost_Savings__c: projectDetails.stage4_energy_cost_savings,
		OEP_EDAPT__Stage4_Natural_Gas_Baseline__c: projectDetails.stage4_natural_gas_consumption_baseline,
		OEP_EDAPT__Stage4_Natural_Gas__c: projectDetails.stage4_natural_gas_consumption,
		OEP_EDAPT__Stage4_Natural_Gas_Savings__c: projectDetails.stage4_natural_gas_consumption_savings,

		OEP_EDAPT__Stage5_Energy_Cost_Baseline__c: projectDetails.stage5_energy_cost_baseline,
		OEP_EDAPT__Stage5_Energy_Cost__c: projectDetails.stage5_energy_cost,
		OEP_EDAPT__Stage5_Electricity_Demand_Baseline__c: projectDetails.stage5_electricity_demand_baseline,
		OEP_EDAPT__Stage5_Electricity_Demand__c: projectDetails.stage5_electricity_demand,
		OEP_EDAPT__Stage5_Electricity_Consumption_Baseline__c: projectDetails.stage5_electricity_consumption_baseline,
		OEP_EDAPT__Stage5_Electricity_Consumption__c: projectDetails.stage5_electricity_consumption,
		OEP_EDAPT__Stage5_Electricity_Consumption_Savings__c: projectDetails.stage5_electricity_consumption_savings,
		OEP_EDAPT__Stage5_Electricity_Demand_Savings__c: projectDetails.stage5_electricity_demand_savings,
		OEP_EDAPT__Stage5_Energy_Cost_Savings__c: projectDetails.stage5_energy_cost_savings,
		OEP_EDAPT__Stage5_Natural_Gas_Baseline__c: projectDetails.stage5_natural_gas_consumption_baseline,
		OEP_EDAPT__Stage5_Natural_Gas__c: projectDetails.stage5_natural_gas_consumption,
		OEP_EDAPT__Stage5_Natural_Gas_Savings__c: projectDetails.stage5_natural_gas_consumption_savings,

		OEP_EDAPT__Stage6_Energy_Cost_Baseline__c: projectDetails.stage6_energy_cost_baseline,
		OEP_EDAPT__Stage6_Energy_Cost__c: projectDetails.stage6_energy_cost,
		OEP_EDAPT__Stage6_Electricity_Demand_Baseline__c: projectDetails.stage6_electricity_demand_baseline,
		OEP_EDAPT__Stage6_Electricity_Demand__c: projectDetails.stage6_electricity_demand,
		OEP_EDAPT__Stage6_Electricity_Consumption_Baseline__c: projectDetails.stage6_electricity_consumption_baseline,
		OEP_EDAPT__Stage6_Electricity_Consumption__c: projectDetails.stage6_electricity_consumption,
		OEP_EDAPT__Stage6_Electricity_Consumption_Savings__c: projectDetails.stage6_electricity_consumption_savings,
		OEP_EDAPT__Stage6_Electricity_Demand_Savings__c: projectDetails.stage6_electricity_demand_savings,
		OEP_EDAPT__Stage6_Energy_Cost_Savings__c: projectDetails.stage6_energy_cost_savings,
		OEP_EDAPT__Stage6_Natural_Gas_Baseline__c: projectDetails.stage6_natural_gas_consumption_baseline,
		OEP_EDAPT__Stage6_Natural_Gas__c: projectDetails.stage6_natural_gas_consumption,
		OEP_EDAPT__Stage6_Natural_Gas_Savings__c: projectDetails.stage6_natural_gas_consumption_savings,
		
		OEP_EDAPT__DT_Estimated_Incentive__c: projectDetails.dt_estimated_incentive,
		OEP_EDAPT__DT_Paid_Date__c: getDate(projectDetails.dt_paid_date),
		OEP_EDAPT__Estimated_P4P1__c: projectDetails.estimated_p4p1,
		OEP_EDAPT__P4P1_Paid_Date__c: getDate(projectDetails.p4p1_paid_date),
		OEP_EDAPT__Estimated_P4P2__c: projectDetails.estimated_p4p2,
		OEP_EDAPT__P4P2_Paid_Date__c: getDate(projectDetails.p4p2_paid_date),
		OEP_EDAPT__Estimated_P4P3__c: projectDetails.estimated_p4p3,
		OEP_EDAPT__P4P3_Paid_Date__c: getDate(projectDetails.p4p3_paid_date),
		OEP_EDAPT__Total_Incremental_Capital_Cost__c: projectDetails.total_incremental_capital_cost,
		OEP_EDAPT__Percent_Incremental_Cost__c: projectDetails.percent_of_incremental_cost,
]
return [OEP_EDAPT__Project__c]


def getDate(stringDate)
{
	if (!stringDate)
		return null
	return new SimpleDateFormat('yyyy-MM-dd').parse(stringDate)
}

def getDateTime(stringDate)
{
	if (stringDate == null || stringDate == '')
		return null
	SimpleDateFormat sdf = new SimpleDateFormat('yyyy-MM-dd HH:mm:ss')
	Date date = sdf.parse(stringDate)
	return date
	
}


