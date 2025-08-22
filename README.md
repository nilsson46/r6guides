# Project Name

This is a setup guide tool for Rainbow Six Siege. The main idea is to help people setup their sites. 

## Getting Started

Instructions for getting a copy of the project up and running on your local machine for development and testing.



### Setup

What needs to be installed to run the project:

- [Java](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) 
- [Maven](https://maven.apache.org/install.html)
- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

### Installation

Step-by-step instructions to install and run the project:

1. Clone the repo:
    ```sh
    git clone https://github.com/nilsson46/r6guides.git
    cd your-repo
    ```

2. Build the project with Maven:
    ```sh
    mvn clean install
    ```

3. Lombok installation:
    - If you are using IntelliJ IDEA, install the Lombok plugin from the marketplace.
    - Enable annotation processing in your IDE settings.

4. Start the database with Docker Compose:
    ```sh
    docker-compose up -d
    ```
5. Run the application:
    ```sh
    mvn spring-boot:run
    ```


## Testing

Instructions for how to run the project’s tests:

```sh
mvn test


Just nu verkar det som att bilderna sparas med linjerna på sig. Fixa det! Eventuellt: 

1. Spara bakgrundsbilden separat (en gång)
När du laddar upp en ny karta, spara bara bilden (utan linjer): 
@PostMapping("/upload-background")
public ResponseEntity<Long> uploadBackground(@RequestParam("file") MultipartFile file,
                                             @RequestParam("name") String name,
                                             @RequestParam("description") String description,
                                             @RequestParam("userId") Long userId) {
    try {
        Map map = new Map();
        map.setName(name);
        map.setDescription(description);
        map.setUserId(userId);
        map.setImageData(file.getBytes());
        map = mapService.addMap(map);
        return ResponseEntity.ok(map.getId());
    } catch (IOException e) {
        return ResponseEntity.internalServerError().build();
    }
}

2. Spara/uppdatera lines separat
När du sparar en strategi, skicka bara lines-arrayen och map-id: 

@PostMapping("/{mapId}/lines")
public ResponseEntity<?> saveLines(@PathVariable Long mapId, @RequestBody List<LineDTO> lines) {
    Map map = mapService.getMapById(mapId);
    if (map == null) return ResponseEntity.notFound().build();

    // Radera gamla lines för denna map
    lineRepository.deleteByMapId(mapId);

    // Spara nya lines
    List<Line> newLines = lines.stream().map(dto -> {
        Line l = new Line();
        l.setPoints(dto.getPoints());
        l.setColor(dto.getColor());
        l.setStrokeWidth(dto.getStrokeWidth());
        l.setMap(map);
        return l;
    }).toList();
    lineRepository.saveAll(newLines);

    return ResponseEntity.ok().build();
}

3. Ladda strategi (bild + lines)
@GetMapping("/{id}/All")
public ResponseEntity<MapWithImageAndLinesDTO> getMapWithImageAndLines(@PathVariable Long id) {
    Map map = mapService.getMapById(id);
    if (map == null) return ResponseEntity.notFound().build();

    MapWithImageAndLinesDTO dto = new MapWithImageAndLinesDTO();
    dto.setId(map.getId());
    dto.setName(map.getName());
    dto.setDescription(map.getDescription());
    dto.setUserId(map.getUserId());
    dto.setImageData(map.getImageData());
    dto.setImageUrl(map.getImageUrl());

    List<LineDTO> lineDTOs = map.getLines().stream().map(line -> {
        LineDTO l = new LineDTO();
        l.setPoints(line.getPoints());
        l.setColor(line.getColor());
        l.setStrokeWidth(line.getStrokeWidth());
        return l;
    }).toList();
    dto.setLines(lineDTOs);

    return ResponseEntity.ok(dto);
} 

4. I frontend
Visa bakgrundsbilden (utan linjer) i <Image />.
Rendera lines-arrayen ovanpå bilden.
När du sparar, skicka lines separat till /maps/{mapId}/lines.