FROM openjdk:17-alpine
ENV APP_HOME /app
EXPOSE 8060
COPY target/gateway-*.jar $APP_HOME/app.jar
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -Djava.security.egd=file:/dev/./urandom -jar app.jar"]