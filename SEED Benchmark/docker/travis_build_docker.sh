#!/bin/bash -x

set -e

IMAGETAG=skip
if [ "${TRAVIS_BRANCH}" == "develop" ]; then
    IMAGETAG=develop
elif [ "${TRAVIS_BRANCH}" == "master" ]; then
    # Retrieve the version number from the OEI/pom.xml file
    IMAGETAG=$( cat "SEED Benchmark/OEI/pom.xml"  | sed '2 s/xmlns=".*"//g' | xmllint --xpath "string(/project/version)" -)
fi

echo $IMAGETAG

if [ "${IMAGETAG}" != "skip" ] && [ "${TRAVIS_PULL_REQUEST}" == "false" ]; then
    docker build -t seedplatform/oep "SEED Benchmark"
    docker login -u $DOCKER_USER -p $DOCKER_PASS

    echo "Tagging image as $IMAGETAG"
    docker tag seedplatform/oep seedplatform/oep:$IMAGETAG
    docker push seedplatform/oep:$IMAGETAG
else
    echo "Not on a deployable branch, this is a pull request or not on the correct branch"
fi
