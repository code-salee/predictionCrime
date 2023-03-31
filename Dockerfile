# Build Stage

FROM openjdk:8-jdk-alpine
MAINTAINER mamadou
COPY target/prediction_crime-0.0.1-SNAPSHOT.jar prediction_crime-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/prediction_crime-0.0.1-SNAPSHOT.jar"]
