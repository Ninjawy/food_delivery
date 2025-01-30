FROM maven:3.9.5-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . /app
RUN mvn dependency:resolve dependency:resolve-plugins
EXPOSE 8080
ENTRYPOINT ["./mvnw", "spring-boot:run","-Dspring-boot.run.profiles=docker"]
