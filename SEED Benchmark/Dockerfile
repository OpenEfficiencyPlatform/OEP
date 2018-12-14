# AUTHOR:           Nicholas Long <nicholas.long@nrel.gov>
# DESCRIPTION:      Mulesoft container with OEI application for connecting SEED to Salesforce

FROM java:openjdk-8-jdk
MAINTAINER nicholas.long@nrel.gov

# Install a couple nice to haves
RUN apt-get update \
	&& apt-get install -y --no-install-recommends \
	    curl \
	    vim \
	&& rm -rf /var/lib/apt/lists/*

RUN cd ~ \
    && wget https://repository-master.mulesoft.org/nexus/content/repositories/releases/org/mule/distributions/mule-standalone/3.8.0/mule-standalone-3.8.0.tar.gz \
    && echo "d9279b3f0373587715613341a16483f3 mule-standalone-3.8.0.tar.gz" | md5sum -c \
    && cd /opt \
    && tar xvzf ~/mule-standalone-3.8.0.tar.gz \
    && rm ~/mule-standalone-3.8.0.tar.gz \
    && ln -s /opt/mule-standalone-3.8.0 /opt/mule

# set the mule environment
ENV MULE_HOME /opt/mule

# copy over the OEI application
COPY /OEI /opt/mule/apps/oei/

VOLUME ["/opt/mule/logs", "/opt/mule/conf", "/opt/mule/apps", "/opt/mule/domains"]
WORKDIR /opt/mule
CMD [ "/opt/mule/bin/mule" ]
EXPOSE 8081
