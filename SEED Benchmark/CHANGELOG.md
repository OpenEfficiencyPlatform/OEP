# OEP / SEED Benchmark

## 1.4

* Labels on the property details were not moved. This supports the labels on PropertyDetails instead of PropertyDetails.property. 
* Remove old unused copy of various files

## 1.3

**Note that this version will not work with SEED < 2.6**
* Update SEED label endpoint to access property_view in SEED Version 2.6. SEED recently updated the backend database relationship of labels to be attached to property views and not properties.
* Enhanced logging of SEED calls to better track down potential errors/issues.


## 1.2

* Better logging when there are duplicates in Salesforce
* Allow user to persist the lastReadDate.txt file to minimize rescans when deploying updated containers

## 1.1

* Allow sending email over SMTP with TLS and specify the port

## 1.0.0-SNAPSHOT

* Initial release
