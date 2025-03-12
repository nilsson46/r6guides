# Första fas: Byggfasen
FROM maven:3.8.4-openjdk-17-slim AS build

# Sätt arbetskatalogen
WORKDIR /app

# Kopiera pom.xml och ladda ner beroenden offline
COPY pom.xml .
RUN mvn dependency:go-offline

# Kopiera resten av koden och bygg applikationen
COPY . .
RUN mvn clean install

# Andra fas: Runtime-fasen
FROM openjdk:17-jdk-slim

# Sätt arbetskatalogen för runtime
WORKDIR /app

# Kopiera den byggda JAR-filen från byggfasen
COPY --from=build /app/target/*.jar /app/app.jar

# Exponera den port som applikationen använder (justera efter behov)
EXPOSE 8080

# Kör applikationen
CMD ["java", "-jar", "/app/app.jar"]
