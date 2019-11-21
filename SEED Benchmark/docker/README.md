# Deployment with Docker

Docker is the preferred method for deploying the SEED Benchmark use case with MuleSoft. The [Dockerfile](../Dockerfile) installs MuleSoft and configures the OEI application for use. The user must follow the configuration instructions below to ensure the system works as expected.

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

The configuration is setup entirely with environment variables to enable easy deployment of multiple instances of MuleSoft. The OEP docker container expects the following environment variables to be set:
      
* *SEED_USER:* Username (in the form of an email address) for SEED.
* *SEED_APIKEY:* API key from SEED. Available from account settings->developer page.
* *SEED_PROTOCOL:* Either HTTP or HTTPS.
* *SEED_URL:* URL of the hosted SEED instance (without http(s)://).
* *SEED_PORT:* Port of the hosted SEED instance (typically 443).
* *OEP_CRON_TIMER:* Interval to check for updated data. 
    * Default is every hour: `0 0 0/1 ? * * *`
    * Trigger every minute for debugging: `0 0/1 0 ? * * *`
    * See https://www.freeformatter.com/cron-expression-generator-quartz.html for more options.
* *SALESFORCE_URL:* URL for Salesforce (with http(s)://).
* *SALESFORCE_USER:* Salesforce user.
* *SALESFORCE_PASSWORD:* Salesforce password.
* *SALESFORCE_TOKEN:* Token to access Salesforce.
* *SALESFORCE_ACCOUNT_TYPE:* Type of Salesforce account type. Defaults to `01236000000SWE1AAO`.
* *SMTP_SEND_ERRORS:* Either `true` or `false`. If `true` then the following must be specified:
    * *SMTP_USER:* SMTP user (typically this is an email address).
    * *SMTP_RECIPIENT:* To whom the email is sent.
    * *SMTP_INTERNAL:* Internal (to the company) to whom a copy of the email is sent.
    * *SMTP_SENDER:* The name of the person sending the email.
    * *SMTP_SUBJECT:* Subject of the sent email message.
    * *SMTP_HOST:* SMTP host domain (e.g. `smtp.gmail.com`).
    * *SMTP_PASSWORD:* Password for SMTP host.
    * *SMTP_PORT:* SMTP Port (e.g., 25, 465, 587) 
    * *SMTP_SECURE:* *Optional*, Use TLS. Note if using gmail, then this must be true.
    
If the environment variables are not set inside the containter, then the application will report an error and fail to launch. 

To test the setup locally, it is recommended to user docker-compose; however, it is possible to use docker if all the environment variables are passed. The issue with using docker instead of docker-compose is that the list of environment variables is long and may be difficult to manage when executing from the command line. Executing using docker-compose allows the user to easily see and edit the variables as needed.

### Docker

It is not recommended to use docker by itself for deployment, however, it is effective for testing and building the container if needed. Note that using docker-compose will also allow fallback on default values as needed. Using the docker command line will require specifying all of the environment variables.

```bash
# Assuming no SMTP
docker run -it --rm -e SEED_USER='SEED_USER' \
 -e SEED_APIKEY='SEED_APIKEY' \
 -e SEED_PROTOCOL='SEED_PROTOCOL' \
 -e SEED_URL='SEED_URL' \
 -e SEED_PORT='SEED_PORT' \
 -e OEP_CRON_TIMER='OEP_CRON_TIMER' \
 -e SALESFORCE_URL='SALESFORCE_URL' \
 -e SALESFORCE_USER='SALESFORCE_USER' \
 -e SALESFORCE_PASSWORD='SALESFORCE_PASSWORD' \
 -e SALESFORCE_TOKEN='SALESFORCE_TOKEN' \
 -e SALESFORCE_ACCOUNT_TYPE='SALESFORCE_ACCOUNT_TYPE' \
 -e SMTP_SEND_ERRORS='false' \
 oei
``` 

### Docker Compose

Using Docker Compose is the preferred method for deploying OEP. The base docker-compose.yml file contains only enough information to build the containers. Make sure to follow the instructions below to configure the application entirely.

Docker-compose nicely encapsulates attached volumes, environment variables, and various other items. Docker compose also integrates will with docker swarm and docker stack deploy. 

1. Copy over the docker-compose.yml file to a newly created and named local file (e.g. docker-compose.city-sf.yml). 

2. Set the environment variables for the city as needed.

3. (Optional) It is possible to store the "lastReadDate.txt" file on the main server to prevent the rescanning of the entire project upon updating the container. To persist the file, then create the file locally with the following script (note that the location of the file should be based on the city microservice being launched):

    ```bash
    cd <project-root>
    mkdir oep/city-1
    echo "2000-01-01 00:00:00.000" > oep/city-1/lastReadDate.txt 
    ```

4. Build the container and launch

    ```bash
    docker-compose -f docker-compose.city-sf.yml build
    docker-compose -f docker-compose.city-sf.yml up
    ```

*Note that multiple cities can be configured in the docker-compose file such as* 

```yaml
# Using Docker Hub Image with Multiple Cities
services:
  oep-city-1:
    build: .
    image: seedplatform/oep
    environment:
      - SEED_USER=user1@domain.com
      - SEED_APIKEY
      - SEED_PROTOCOL=HTTPS
      - SEED_URL
      - SEED_PORT
      - OEP_CRON_TIMER=0 0 0/1 ? * * *
      - SALESFORCE_URL=https://test.salesforce.com/services/Soap/u/37.0
      - SALESFORCE_USER
      - SALESFORCE_PASSWORD
      - SALESFORCE_TOKEN
      - SALESFORCE_ACCOUNT_TYPE=01236000000SWE1AAO
      - SMTP_SEND_ERRORS=false
      - SMTP_SUBJECT=Error log for SEED to Salesforce Mule process
      - SMTP_USER
      - SMTP_RECIPIENT
      - SMTP_INTERNAL
      - SMTP_SENDER
      - SMTP_HOST
      - SMTP_PASSWORD
      - SMTP_PORT
      - SMTP_SECURE
    #volumes:
    #  - $PWD/oep/city-1/lastReadDate.txt:/tmp/oep/lastReadDate.txt
  oep-city-2:
    build: .
    image: seedplatform/oep
    environment:
      - SEED_USER=user2@domain.com
      - SEED_APIKEY
      - SEED_PROTOCOL=HTTPS
      - SEED_URL
      - SEED_PORT
      - OEP_CRON_TIMER=0 0 0/1 ? * * *
      - SALESFORCE_URL=https://test.salesforce.com/services/Soap/u/37.0
      - SALESFORCE_USER
      - SALESFORCE_PASSWORD
      - SALESFORCE_TOKEN
      - SALESFORCE_ACCOUNT_TYPE=01236000000SWE1AAO
      - SMTP_SEND_ERRORS=false
      - SMTP_SUBJECT=Error log for SEED to Salesforce Mule process
      - SMTP_USER
      - SMTP_RECIPIENT
      - SMTP_INTERNAL
      - SMTP_SENDER
      - SMTP_HOST
      - SMTP_PASSWORD
      - SMTP_PORT
      - SMTP_SECURE
```
