FROM eclipse-temurin:17-jdk-alpine
LABEL org.opencontainers.image.authors="bschalme@airspeed.ca"
RUN apk --no-cache add curl
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
