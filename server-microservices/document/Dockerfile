# Use an official OpenJDK 21 runtime as a parent image
FROM openjdk:21-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file to the container
COPY target/document-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on
EXPOSE 8002

# Set the environment variable for the Eureka host, port
ENV EUREKA_URL=172.31.91.243:8761
ENV DB_URL=jdbc:mysql://172.31.91.243:3306/TEMP?autoReconnect=true
ENV DB_USERNAME=root
ENV DB_PASSWORD=naman


# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
