### Usage

Build the apps with images (we need ji for `config` since it contains `curl`):
```shell
$ mvn clean package -Pbuild-image
$ mvn -DskipTests clean package
```

Then run all the containers with `docker-compose`:
```shell
$ docker-compose up --build
```

Then stop all the containers with `docker-compose`:
```shell
$ docker-compose down
```

In the most cases you need to have Maven and JDK8+. In the fourth example with OpenShift you will have to run **Minishift** on your machine. The best way to run the sample applications is with IDEs like IntelliJ IDEA or Eclipse.

## Architecture

Our sample microservices-based system consists of the following modules:
- **gateway** - a module that Spring Cloud for running Spring Boot application that acts as a proxy/gateway in our architecture.
- **config** - a module that uses Spring Cloud Config Server for running configuration server in the `native` mode. The configuration files are placed on the classpath.
- **eureka** - a module that depending on the example it uses Spring Cloud Netflix Eureka as an embedded discovery server.
- **transport-service** - a module containing the first of our sample microservices that allows to perform CRUD operation on in-memory repository of transports and drivers
- **passenger-service** - a module containing the second of our sample microservices that allows to perform CRUD operation on in-memory repository of departments. It communicates with transport-service.

