# Read Me First
The following was discovered as part of building this project:

* No Docker Compose services found. As of now, the application won't start! Please add at least one service to the `compose.yaml` file.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.2/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Config Client Quick Start](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/#_client_side_usage)
* [Spring Data R2DBC](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsingle/index.html#data.sql.r2dbc)
* [Liquibase Migration](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsingle/index.html#howto.data-initialization.migration-tool.liquibase)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsingle/index.html#web)
* [Docker Compose Support](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsingle/index.html#features.docker-compose)
* [Spring Reactive Web](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsingle/index.html#web.reactive)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with R2DBC](https://spring.io/guides/gs/accessing-data-r2dbc/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Building a Reactive RESTful Web Service](https://spring.io/guides/gs/reactive-rest-service/)

### Additional Links
These additional references should also help you:

* [R2DBC Homepage](https://r2dbc.io)

### Docker Compose support
This project contains a Docker Compose file named `compose.yaml`.

However, no services were found. As of now, the application won't start!

Please make sure to add at least one service in the `compose.yaml` file.

## Missing R2DBC Driver

Make sure to include a [R2DBC Driver](https://r2dbc.io/drivers/) to connect to your database.

mvn spring-boot:run -Dspring-boot.run.jvmArguments=-Dserver.port=9005
http://localhost:9005/
http://localhost:9005/swagger-ui.html
http://localhost:9005/webjars/swagger-ui/index.html

https://piotrminkowski.com/2023/07/28/reactive-spring-boot-with-webflux-r2dbc-and-postgres/