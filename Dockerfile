# Använd OpenJDK 17 som basbild
FROM openjdk:17-jdk

# Installera Maven
RUN apt-get update && apt-get install -y maven

# Ställ in arbetskatalogen
WORKDIR /app

# Kopiera över pom.xml
COPY pom.xml .

# Ladda ner beroenden för Maven
RUN mvn dependency:go-offline

# Kopiera hela applikationen till containern
COPY . .

# Bygg applikationen
RUN mvn clean install

# Starta applikationen
CMD ["java", "-jar", "target/your-app.jar"]
