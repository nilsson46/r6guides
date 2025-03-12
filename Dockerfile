# Start from a fresh Ubuntu image
FROM ubuntu:20.04

# Set up working directory
WORKDIR /app

# Install OpenJDK 17 and Maven
RUN apt-get update && apt-get install -y openjdk-17-jdk maven

# Copy the pom.xml to container
COPY pom.xml .

# Download the dependencies offline
RUN mvn dependency:go-offline

# Copy the rest of the application
COPY . .

# Build the application
RUN mvn clean install

# Run the application
CMD ["java", "-jar", "target/your-app.jar"]
