# Use an official OpenJDK 21 runtime as a parent image
FROM openjdk:21-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file to the container
COPY target/gateway-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Set the environment variable for the Eureka host, port
# ENV EUREKA_URL=http://172.31.91.243:8761/eureka/


# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
