FROM maven:3.8.5-openjdk-17 AS build
COPY . .

RUN mvn package -X

FROM openjdk:17.0.1-jdk-slim

COPY --from=build /target/server-1.jar /app/server.jar

# Set the working directory in the container
WORKDIR /app

# Expose the port the application runs on
EXPOSE 7777

ENTRYPOINT ["java", "-jar", "/app/server.jar"]