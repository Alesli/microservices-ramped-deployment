#!/usr/bin/bash

./mvnw clean package
docker login --username=epamalesiaskarakhod
docker build -t postapp-image -f DockerfilePostApp .
docker tag postapp-image epamalesiaskarakhod/postapp-image:1.0.0
docker push epamalesiaskarakhod/postapp-image:1.0.0

docker build -t epamalesiaskarakhod/postrapp-image-1.0.0 ./
docker login
docker push epamalesiaskarakhod/postapp-image-1.0.0
docker run -ti -e DB_URL='jdbc:postgresql://posts-db-container:5433/postsdb' -e DB_USER='root' -e DB_PASSWORD='password' -p 8089:8089 -d epamalesiaskarakhod/userapp-image-1.0.0