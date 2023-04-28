#!/usr/bin/bash

./mvnw clean package
docker login --username=epamalesiaskarakhod
docker build -t epamalesiaskarakhod/postapp-image:1.0.0 -f DockerfilePostApp .
docker push epamalesiaskarakhod/postapp-image:1.0.0

docker run -ti -e DB_URL='jdbc:postgresql://posts-db-container:5432/postsdb' -e DB_USER='root' -e DB_PASSWORD='password' -p 8089:8089 -d epamalesiaskarakhod/postapp-image-1.0.0