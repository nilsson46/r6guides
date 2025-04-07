# Project Name

A short description of what the project does and why it is useful.

## Getting Started

Instructions for getting a copy of the project up and running on your local machine for development and testing.

### Prerequisites

What needs to be installed to run the project:

- [Java](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) 
- [Maven](https://maven.apache.org/install.html)
- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

### Installation

Step-by-step instructions to install and run the project:

1. Clone the repo:
    ```sh
    git clone https://github.com/your-username/your-repo.git
    cd your-repo
    ```

2. Build the project with Maven:
    ```sh
    mvn clean install
    ```

3. Start the database with Docker Compose:
    ```sh
    docker-compose up -d
    ```

4. Run the application:
    ```sh
    mvn spring-boot:run
    ```

## Usage

Examples of how to use the project, with code examples and screenshots if possible.

## Testing

Instructions for how to run the project’s tests:

```sh
mvn test
