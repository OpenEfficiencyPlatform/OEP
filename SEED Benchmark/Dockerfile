# AUTHOR:           Nicholas Long <nicholas.long@nrel.gov>
# DESCRIPTION:      Mulesoft container with OEI application for connecting SEED to Salesforce

FROM java:openjdk-8-jdk
MAINTAINER nicholas.long@nrel.gov

ENV MULE_VERSION 3.8.0
ENV MULE_SHA d9279b3f0373587715613341a16483f3

# Install a couple nice to haves and mulesoft
RUN apt-get update && apt-get install -y --no-install-recommends \
	    curl \
	    vim \
    && curl -SL https://repository-master.mulesoft.org/nexus/content/repositories/releases/org/mule/distributions/mule-standalone/$MULE_VERSION/mule-standalone-$MULE_VERSION.tar.gz -o /opt/mule-standalone-$MULE_VERSION.tar.gz \
    && echo "$MULE_SHA /opt/mule-standalone-$MULE_VERSION.tar.gz" | md5sum -c \
    && cd /opt \
    && tar xvzf mule-standalone-$MULE_VERSION.tar.gz \
    && rm /opt/mule-standalone-$MULE_VERSION.tar.gz \
    && ln -s /opt/mule-standalone-$MULE_VERSION /opt/mule \
    && rm -rf /var/lib/apt/lists/*

# set the mule environment
ENV MULE_HOME /opt/mule

# copy over the OEI application
COPY /OEI /opt/mule/apps/oei/
COPY /conf/oei.properties /opt/mule/conf/oei.properties

# save the entry point to the server to be executed
COPY /docker/entrypoint.sh /usr/local/bin/mule-entrypoint
COPY /docker/start_mule.sh /usr/local/bin/start_mule

ENTRYPOINT ["mule-entrypoint"]

VOLUME ["/opt/mule/logs", "/opt/mule/domains"]
WORKDIR /opt/mule
CMD [ "/usr/local/bin/start_mule" ]
EXPOSE 8081
