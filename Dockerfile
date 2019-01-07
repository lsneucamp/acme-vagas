FROM maven:3.3-jdk-8 AS build

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -Dmaven.test.skip=true


FROM openjdk:8-slim

COPY --from=build /usr/src/app/target/vagas-api-*.jar /usr/app/app.jar

EXPOSE 10001
CMD ["java", "-jar","/usr/app/app.jar"]

