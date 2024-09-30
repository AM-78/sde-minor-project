# Use an official OpenJDK 21 runtime as a parent image
FROM openjdk:21-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file to the container
COPY target/eureka-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on
EXPOSE 8761

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
