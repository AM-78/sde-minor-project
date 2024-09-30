# Use an official OpenJDK 21 runtime as a parent image
FROM openjdk:21-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file to the container
COPY target/writer-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on
EXPOSE 8003

# Set the environment variable for the Eureka host, port
# ENV EUREKA_URL=http://eureka:8761/eureka/
# ENV DB_URL=jdbc:mysql://34.93.102.202/TEMP?autoReconnect=true
# ENV DB_USERNAME=root
# ENV DB_PASSWORD=naman

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
