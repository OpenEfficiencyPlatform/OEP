# Deployment with Docker

Docker is the preferred method for deploying the SEED Benchmark use case with MuleSoft. The [Dockerfile](../Dockerfile) installs MuleSoft and configure the OEI application for use. The user must follow the configuration instructions below to ensure the system works as expected.

To develop and test locally the following dependency stack is recommended. 

* Install Docker (Version 17.09.0 or greater is required)
    * OSX Users: [install Docker CE for Mac](https://docs.docker.com/docker-for-mac/install/). Please refer to [this guide](https://docs.docker.com/docker-for-mac/install/)
    * Windows 10 Users: [Docker CE for Windows](https://docs.docker.com/docker-for-windows/install/). More information 
    can be found in [this guide](https://docs.docker.com/docker-for-windows/). 
    * Linux Users: Follow the instructions in the [appropriate guide](https://www.docker.com/community-edition)
    
* Install Docker Compose (Version 1.17.0 or greater is required)
    * Docker compose will be installed on Mac and Windows by default
    * Linux Users: See instructions [here](https://docs.docker.com/compose/install/)

## Configuration

The configuration files need to be installed in the `conf` folder (without subdirectories). If using 
docker, then create a sub-directory for each organization (e.g. sanfrancisco) and copy the 
`oei.properties.template` file into the directory, remove the `.template`, and populate the file
with the correct credentials.

Use the following command when launching an instance to save your credentials into the right place.

```bash
docker run -it --rm -v "$(pwd)/conf/sanfrancisco/oei.properties":/opt/mule/conf/oei.properties oei
``` 

If using Docker Compose, then you will need to ensure that the oei.properties files are correctly mounted into the container. It is recommended to use the snippet below:

```yaml
# Using Docker Hub Image:
oep-city_a:
  image: seedplatform/oep:1.0.0-SNAPSHOT
  volumes:
    - "$(pwd)/conf/sanfrancisco/oei.properties":/opt/mule/conf/oei.properties
```
