#!/usr/bin/bash

./mvnw clean package
docker login --username=epamalesiaskarakhod
docker build -t epamalesiaskarakhod/postapp-image:2.0.0 -f DockerfilePostApp .
docker push epamalesiaskarakhod/postapp-image:2.0.0