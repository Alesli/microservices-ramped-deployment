#!/usr/bin/bash

./mvnw clean package
docker login --username=epamalesiaskarakhod
docker build -t epamalesiaskarakhod/userapp-image:1.0.0 -f DockerfileUserApp .
docker push epamalesiaskarakhod/userapp-image:1.0.0