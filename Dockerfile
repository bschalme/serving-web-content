FROM eclipse-temurin:17-jdk-alpine
LABEL org.opencontainers.image.authors="bschalme@airspeed.ca"
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]