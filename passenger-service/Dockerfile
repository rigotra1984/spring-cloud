FROM openjdk:17-alpine
ENV APP_HOME /app
EXPOSE 8080
COPY target/passenger-service-*.jar $APP_HOME/app.jar
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -Djava.security.egd=file:/dev/./urandom -jar app.jar"]