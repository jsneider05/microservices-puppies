# User Microservice

1) Environment variables for this Microservice

```properties
PUPPIES_USER_ACTIVE_PROFILE
PUPPIES_USER_SERVER_PORT
PUPPIES_USER_DATABASE_URL
PUPPIES_USER_DATABASE_USERNAME
PUPPIES_USER_DATABASE_PASSWORD
```

2) Run the project as a jar
```bash
cd {folder-name}/user/target
java -jar ${project.name}-${project.parent.version}-${java.version}.jar

cd microservices-puppies/user/target
java -jar user-0.0.1-SNAPSHOT-17.jar
```

Or, as a Spring Boot Application with

```bash
mvn spring-boot:run
```

## Swagger

|Resource|URL|
|--------|---|
|UI|[http://localhost:8081/api/swagger-ui.html](http://localhost:8080/api/swagger-ui.html)|
|Json|[http://localhost:8081/api/v3/api-docs](http://localhost:8080/api/v3/api-docs)|

## Postman Collection and Environment

|Resource|Path|
|--------|---|
|PUPPIES.postman_collection.json|[documentation/postman/PUPPIES.postman_collection.json](documentation/postman/PUPPIES.postman_collection.json)|
|Puppies-Dev.postman_environment.json|[documentation/postman/Puppies-Dev.postman_environment.json](documentation/postman/Puppies-Dev.postman_environment.json)|
