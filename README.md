# OEP
OEP Overview: 
Open Efficiency Platform (OEP) is an initiative to help commercial whole building energy efficiency programs to be more efficient and gain more value from data. It involved pilots that focused on exploring use cases for existing Federal tools and industry standards and their integration to support programs. The effort was funded by the U.S. Department of Energy and was a collaboration with The Energy Coalition (TEC), City of San Francisco, Xcel Energy, NREL, Cadmus, SKEE, and Vermont Energy Investment Corportation (VEIC).

This repository houses open source resources developed as part of OEP; made publicly available to support the adoption by the industry of these resources. It includes folders for documentation of a full Benchmark use case, the OEI Salesforce Manged Package, and sample code for connecting to Energy Design Assistance Program Tracker (EDAPT), ENERGY STAR Portfolio Manager (ESPM), and Green Button Connect (GBC) OAuth 2.0.   

SEED Benchmark: 
The Benchmark use case was piloted with the City of San Francisco to support their Benchmarking mandate. The City of San Francisco is importing ESPM benchmarking data into SEED. They are then transfering data from SEED into Salesforce to support reporting and CRM functions like emailing contacts. The OEP use case provides the automation of data from SEED to Salesforce via MuleSoft Community Edition API. It uses the OEP Salesforce Managed Package that includes a custom Benchmark object and the Account and Contact standard Salesforce objects. Automation of data flow between SEED and Salesforce is triggered by SEED Labels depending if the Benchmark has complied or not. 

Salesforce: The OEP Salesforce Mangaged Package was piloted with the City of San Francisco to support their Benchmark mandate and developed in collaboration with Xcel Energy to support their program management use case using EDAPT. It is designed to support program transactions, pipeline tracking, reporting, and CRM functionality. The Managed Package is Building Energy Data Exchange Specification (BEDES) compliant and was designed to integrate with BuildingSync XML. It includes field mappings from ENERGY STAR Porfolio Manager, Asset Score, and EDAPT.  
The EDAPT sample code is sample code that was developed for a use case where EDAPT was connected to Salesforce via MuleSoft Community Edition API. 

The ESPM sample code...

The GBC OAuth 2.0 sample code...
