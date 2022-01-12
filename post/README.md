# Post Microservice

1) Environment variables for this Microservice

```properties
PUPPIES_POST_ACTIVE_PROFILE
PUPPIES_POST_SERVER_PORT
PUPPIES_POST_DATABASE_URL
PUPPIES_POST_DATABASE_USERNAME
PUPPIES_POST_DATABASE_PASSWORD
PUPPIES_POST_BUCKET_NAME
```

2) Run the project as a jar
```bash
cd {folder-name}/post/target
java -jar ${project.name}-${project.parent.version}-${java.version}.jar

cd microservices-puppies/post/target
java -jar post-0.0.1-SNAPSHOT-17.jar
```

Or, as a Spring Boot Application with

```bash
mvn spring-boot:run
```

## Swagger

|Resource|URL|
|--------|---|
|UI|[http://localhost:8080/api/swagger-ui.html](http://localhost:8080/api/swagger-ui.html)|
|Json|[http://localhost:8080/api/v3/api-docs](http://localhost:8080/api/v3/api-docs)|

## Postman Collection and Environment

|Resource|Path|
|--------|---|
|PUPPIES.postman_collection.json|[documentation/postman/PUPPIES.postman_collection.json](documentation/postman/PUPPIES.postman_collection.json)|
|Puppies-Dev.postman_environment.json|[documentation/postman/Puppies-Dev.postman_environment.json](documentation/postman/Puppies-Dev.postman_environment.json)|
