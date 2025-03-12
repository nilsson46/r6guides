# Använd en officiell Java 17-bild som bas
FROM eclipse-temurin:17-jdk-slim
  
  # Ställ in arbetskatalogen i containern
WORKDIR /app
  
  # Kopiera över Maven pom.xml och ladda ner beroenden (för caching)
COPY pom.xml .
RUN mvn dependency:go-offline
  
  # Kopiera hela applikationen till containern
COPY src /app/src
  
  # Bygg applikationen
RUN mvn clean install -DskipTests
  
  # Exponera porten som applikationen kommer att köra på (exempel: 8080)
EXPOSE 8080
  
  # Kör applikationen
CMD ["java", "-jar", "target/your-app.jar"]
