FROM eclipse-temurin:21-jre-alpine

WORKDIR /workspace

RUN mkdir -p /workspace/logs

COPY target/config-server-*.jar app.jar

EXPOSE 8888

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /workspace/app.jar"]