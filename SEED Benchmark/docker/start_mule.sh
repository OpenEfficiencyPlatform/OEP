#!/bin/bash

if [ -v OEP_DISABLED ]; then
    echo "OEP set to be disabled. Exiting gracefully."
    exit 0
fi

if [ -z ${SEED_USER+x} ]; then
    echo "SEED_USER is not set and is required"
    exit 1
fi

if [ -z ${SEED_APIKEY+x} ]; then
    echo "SEED_APIKEY is not set and is required"
    exit 1
fi

if [ -z ${SEED_PROTOCOL+x} ]; then
    echo "SEED_PROTOCOL is not set and is required"
    exit 1
fi

if [ -z ${SEED_URL+x} ]; then
    echo "SEED_URL is not set and is required"
    exit 1
fi

if [ -z ${SEED_PORT+x} ]; then
    echo "SEED_PORT is not set and is required"
    exit 1
fi

if [ -z ${OEP_CRON_TIMER+x} ]; then
    echo "OEP_CRON_TIMER is not set and is required"
    exit 1
fi

if [ -z ${SALESFORCE_URL+x} ]; then
    echo "SALESFORCE_URL is not set and is required"
    exit 1
fi

if [ -z ${SALESFORCE_USER+x} ]; then
    echo "SALESFORCE_USER is not set and is required"
    exit 1
fi

if [ -z ${SALESFORCE_PASSWORD+x} ]; then
    echo "SALESFORCE_PASSWORD is not set and is required"
    exit 1
fi

if [ -z ${SALESFORCE_TOKEN+x} ]; then
    echo "SALESFORCE_TOKEN is not set and is required"
    exit 1
fi

if [ -z ${SALESFORCE_ACCOUNT_TYPE+x} ]; then
    echo "SALESFORCE_ACCOUNT_TYPE is not set and is required"
    exit 1
fi

if [ -z ${SMTP_SEND_ERRORS+x} ]; then
    echo "SMTP_SEND_ERRORS is not set and is required"
    exit 1
fi

if [ ${SMTP_SEND_ERRORS} == "true" ]; then
    if [ -z ${SMTP_SUBJECT+x} ]; then
        echo "SMTP_SUBJECT is not set and is required"
        exit 1
    fi

    if [ -z ${SMTP_USER+x} ]; then
        echo "SMTP_USER is not set and is required"
        exit 1
    fi

    if [ -z ${SMTP_RECIPIENT+x} ]; then
        echo "SMTP_RECIPIENT is not set and is required"
        exit 1
    fi

    if [ -z ${SMTP_INTERNAL+x} ]; then
        echo "SMTP_INTERNAL is not set and is required"
        exit 1
    fi

    if [ -z ${SMTP_SENDER+x} ]; then
        echo "SMTP_SENDER is not set and is required"
        exit 1
    fi

    if [ -z ${SMTP_HOST+x} ]; then
        echo "SMTP_HOST is not set and is required"
        exit 1
    fi

    if [ -z ${SMTP_PASSWORD+x} ]; then
        echo "SMTP_PASSWORD is not set and is required"
        exit 1
    fi

    if [ -z ${SMTP_PORT+x} ]; then
        echo "SMTP_PORT is not set and is required"
        exit 1
    fi

    if [ -z ${SMTP_SECURE+x} ]; then
        echo "SMTP_SECURE is not set, defaulting to false"
        export SMTP_SECURE=false
    fi
else
    # If not using SMTP, then set the env vars to random values. This is needed since
    # mule requires them to exist (even if not used)
    echo "SMTP errors will not be sent. Setting needed env vars to unused values"
    export SMTP_SUBJECT=UNUSED
    export SMTP_USER=UNUSED
    export SMTP_RECIPIENT=UNUSED
    export SMTP_INTERNAL=UNUSED
    export SMTP_SENDER=UNUSED
    export SMTP_HOST=UNUSED
    export SMTP_PASSWORD=UNUSED
    export SMTP_PORT=UNUSED
    export SMTP_SECURE=UNUSED
fi

/opt/mule/bin/mule
