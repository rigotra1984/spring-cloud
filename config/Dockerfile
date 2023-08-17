FROM openjdk:17-alpine
RUN apk --no-cache add curl jq
ENV APP_HOME /app
EXPOSE 8088
COPY target/config-*.jar $APP_HOME/app.jar
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -Djava.security.egd=file:/dev/./urandom -jar app.jar"]