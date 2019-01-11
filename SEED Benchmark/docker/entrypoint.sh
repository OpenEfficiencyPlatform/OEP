#!/bin/bash

# make sure the mule oei log exists and tail it as a background process
touch /opt/mule/logs/mule-app-oei.log
tail -f /opt/mule/logs/mule-app-oei.log &

exec "$@"
