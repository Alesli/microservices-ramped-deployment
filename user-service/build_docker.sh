#!/usr/bin/bash

./mvnw clean package
docker login --username=epamalesiaskarakhod
docker build -t userapp-image -f DockerfileUserApp .
docker tag userapp-image epamalesiaskarakhod/userapp-image:1.0.0
docker push epamalesiaskarakhod/userapp-image:1.0.0

docker build -t epamalesiaskarakhod/userapp-image-1.0.0 ./
docker login
docker push epamalesiaskarakhod/userapp-image-1.0.0
docker run -ti -e DB_URL='jdbc:postgresql://users-db-container:5432/usersdb' -e DB_USER='root' -e DB_PASSWORD='password' -p 8088:8088 -d epamalesiaskarakhod/userapp-image-1.0.0