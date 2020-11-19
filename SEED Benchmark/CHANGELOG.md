# OEP / SEED Benchmark

## 1.6

* Escape quotes when passing data to SEED API
* Support SEED 2.7.4 API v2 and v2.1. This version does not work with SEED APIv3.

## 1.5

* Email log messages will return the correct HOST (instead of hard coded seedv2)
* lastReadDate.txt will be looked for in /tmp/oep/lastReadDate.txt


## 1.4

* Labels on the property details were moved. This supports the labels on PropertyDetails instead of PropertyDetails.property.

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
