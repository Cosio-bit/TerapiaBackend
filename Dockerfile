FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the built JAR file
COPY target/TerapiaBackend-0.0.1-SNAPSHOT.jar app.jar

# Copy application.properties (if it's in src/main/resources)
COPY src/main/resources/application.properties /app/config/application.properties

EXPOSE 8090
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=file:/app/config/application.properties"]
