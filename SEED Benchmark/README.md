# SEED Benchmark

[![Build Status](https://travis-ci.org/SEED-platform/OEP.svg?branch=develop)](https://travis-ci.org/SEED-platform/OEP)

The OEP SEED Benchmark supports Benchmark programs by automting benchmark record data updadates from the Standard Energy Efficiecny Data (SEED) Platform in Salesforce.com. The automation is provided via MuleSoft Anypoint Platform Community Edition API. The MuleSoft API provides one-way data flow, with Property records in SEED updating Benchmark records, and creating Account and Contract records in a Salecforce managed package called the "OEI Package". The OEI Package includes example Benchmark reports and email templates.

This automation was developed to support a pilot project with the City of San Francisco. It is an open source solution that can be configured to meet the needs of other organizations interested in benchmarking data management using SEED and Salesforce.

Get started with: OEP / SEED Benchmark / guides
It includes the OEP Benchmark Implementation Guide and OEP User Guide.

"OEI" folder holds the self extracting zip MuleSoft files to be used for Docker.

"mulesoft" folder holds files using the MuleSoft Anypoint Studio to review or edit the MuleSoft files.


# TODO

* Remove spaces from directory names. It can cause some trivial issues with Docker


