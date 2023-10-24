FROM eclipse-temurin:19-jdk-alpine

LABEL authors="semih.sozdinler"

VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]