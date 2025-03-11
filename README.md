# Projektets Namn

En kort beskrivning av vad projektet gör och varför det är användbart.

## Kom igång

Instruktioner för hur man får en kopia av projektet igång på sin lokala maskin för utveckling och testning.

### Förutsättningar

Vad behöver installeras för att köra projektet:

- [Java](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) 
- [Maven](https://maven.apache.org/install.html)
- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

### Installation

Steg-för-steg instruktioner för att installera och köra projektet:

1. Klona repot:
    ```sh
    git clone https://github.com/ditt-användarnamn/ditt-repo.git
    cd ditt-repo
    ```

2. Bygg projektet med Maven:
    ```sh
    mvn clean install
    ```

3. Starta databasen med Docker Compose:
    ```sh
    docker-compose up -d
    ```

4. Kör applikationen:
    ```sh
    mvn spring-boot:run
    ```

## Användning

Exempel på hur man använder projektet, med kodexempel och skärmdumpar om möjligt.

## Testning

Instruktioner för hur man kör projektets tester:

```sh
mvn test
