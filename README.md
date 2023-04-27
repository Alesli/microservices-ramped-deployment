# microservices-architecture-docker
Posting microservice system and deploy it using Docker.

## Docker configurations for DB postgresql:
### Build the Docker image using the following command:
for users:
- `docker build -t users-db-image -f DockerfileUserDb .`

for posts
- `docker build -t posts-db-image -f DockerfilePostDb .`

### Run a container using the following command:
for users:
- `docker run -p 5432:5432 --name users-db-container -d users-db-image`

for posts
- `docker run -p 5433:5432 --name posts-bd-container -d posts-db-image`

## Docker configurations for application:
### Build the Docker image using the following command:
for user service:
- `docker build -t userapp-image -f DockerfileUserApp .`

for post service
- `docker build -t postapp-image -f DockerfilePostApp .`

#