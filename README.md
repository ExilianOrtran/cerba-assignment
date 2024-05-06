## Database configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/springbootdb

spring.datasource.username=postgres

spring.datasource.password=postgres@123

spring.datasource.driver-class-name=org.postgresql.Driver


##  Running
Run as a standard spring boot application. Application can be triggered using postman.
A postman collection can easily be created using the openapi spec file under src/main/resources.

##  Tests
Tests can be found under src/test/java

Tests are developed using the tests containers framework to test with a real postgresql database. Unfortunately, due to limited time they are currently very limited in scope.

## Future work
- Add unit tests using mockito
- Integration tests using test containers
- HATEOS
- Implement exception handling using ProblemDetail and ControllerAdvice/ExceptionHandler
