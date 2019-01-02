# AUTHOR:           Nicholas Long <nicholas.long@nrel.gov>
# DESCRIPTION:      Mulesoft container with OEI application for connecting SEED to Salesforce

FROM java:openjdk-8-jdk
MAINTAINER nicholas.long@nrel.gov

ENV MULE_VERSION 3.8.0
ENV MULE_SHA d9279b3f0373587715613341a16483f3

# Install a couple nice to haves
RUN apt-get update \
	&& apt-get install -y --no-install-recommends \
	    curl \
	    vim \
	&& rm -rf /var/lib/apt/lists/*

RUN cd ~ \
    && wget https://repository-master.mulesoft.org/nexus/content/repositories/releases/org/mule/distributions/mule-standalone/$MULE_VERSION/mule-standalone-$MULE_VERSION.tar.gz \
    && echo "$MULE_SHA mule-standalone-$MULE_VERSION.tar.gz" | md5sum -c \
    && cd /opt \
    && tar xvzf ~/mule-standalone-$MULE_VERSION.tar.gz \
    && rm ~/mule-standalone-$MULE_VERSION.tar.gz \
    && ln -s /opt/mule-standalone-$MULE_VERSION /opt/mule

# set the mule environment
ENV MULE_HOME /opt/mule

# copy over the OEI application
COPY /OEI /opt/mule/apps/oei/
#COPY /conf/oei.properties /opt/mule/conf/oei.properties

COPY /docker/entrypoint.sh /usr/local/bin/mule-entrypoint
RUN chmod 775 /usr/local/bin/mule-entrypoint
ENTRYPOINT ["mule-entrypoint"]

VOLUME ["/opt/mule/logs", "/opt/mule/conf", "/opt/mule/apps", "/opt/mule/domains"]
WORKDIR /opt/mule
CMD [ "/opt/mule/bin/mule" ]
EXPOSE 8081
