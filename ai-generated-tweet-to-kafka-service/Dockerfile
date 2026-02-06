FROM eclipse-temurin:21-jre-alpine

WORKDIR /workspace

RUN mkdir -p /workspace/logs

COPY target/*.jar app.jar

EXPOSE 5005

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /workspace/app.jar"]