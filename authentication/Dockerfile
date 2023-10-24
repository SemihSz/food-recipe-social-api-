# Submodule 1 Dockerfile

# Use the official OpenJDK base image
FROM eclipse-temurin:19-jdk-alpine

# Set working directory in the container
WORKDIR /app

# Copy the JAR file into the container (replace 'submodule1.jar' with your actual JAR file)
COPY target/submodule1.jar app.jar

# Expose the port your application will listen on
EXPOSE 8080

# Define an environment variable (optional)
ENV SPRING_PROFILES_ACTIVE=production

# Command to run the application
CMD ["java", "-jar", "app.jar"]
