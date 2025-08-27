package com.example.r6guides.controller;

import com.example.r6guides.DTO.LineDTO;
import com.example.r6guides.DTO.MapDataDTO;
import com.example.r6guides.DTO.MapWithImageAndLinesDTO;
import com.example.r6guides.models.Line;
import com.example.r6guides.models.Map;
import com.example.r6guides.repository.MapRepository;
import com.example.r6guides.service.MapService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.r6guides.repository.LineRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/maps")
public class MapController {
    private final LineRepository lineRepository;
    private final MapService mapService;
    private final MapRepository mapRepository;

    @Autowired
    public MapController(MapService mapService, MapRepository mapRepository, LineRepository lineRepository) {
        this.mapService = mapService;
        this.mapRepository = mapRepository;
        this.lineRepository = lineRepository;
    }

    @GetMapping
    public ResponseEntity<List<Map>> getAllMaps() {
        List<Map> maps = mapService.getAllMaps();
        return new ResponseEntity<>(maps, HttpStatus.OK);
    }



    //This one is used in the frontend right now
   /* @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("name") String name, @RequestParam("description") String description) {
        try {
            Map map = new Map();
            map.setName(name);
            map.setDescription(description);
            map.setImageData(file.getBytes());
            map.setImageUrl("");
            mapService.addMap(map); // Save the new Map entity
            return ResponseEntity.ok("Image uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Failed to upload image");
        }
    } */

    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("data") String dataJson
    ) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            MapDataDTO mapData = objectMapper.readValue(dataJson, MapDataDTO.class);

            Map map = new Map();
            map.setName(mapData.getName());
            map.setDescription(mapData.getDescription());
            map.setUserId(mapData.getUserId());
            map.setImageData(file.getBytes());
            map.setImageUrl(""); // Set if you have a URL

            // Save map first to get an id
            map = mapService.addMap(map);

            // Save lines linked to map
            if (mapData.getLines() != null) {
                List<Line> lines = new ArrayList<>();
                for (LineDTO lineDTO : mapData.getLines()) {
                    Line line = new Line();
                    line.setPoints(lineDTO.getPoints());
                    line.setColor(lineDTO.getColor());
                    line.setStrokeWidth(lineDTO.getStrokeWidth());
                    line.setMap(map);
                    lines.add(line);
                }
                lineRepository.saveAll(lines);
            }

            return ResponseEntity.ok("Image and data uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Failed to upload image and data");
        }
    }
    //Try this on in the frontend.
    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) {
        byte[] imageData = mapService.getImage(id);
        if (imageData != null) {
            return ResponseEntity.ok()
                    .header("Content-Type", "image/jpeg") // Adjust based on your image format
                    .body(imageData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/All")
    public ResponseEntity<MapWithImageAndLinesDTO> getMapWithImageAndLines(@PathVariable("id") Long id) {
        Map map = mapService.getMapById(id);
        if (map == null) {
            return ResponseEntity.notFound().build();
        }
        MapWithImageAndLinesDTO dto = new MapWithImageAndLinesDTO();
        dto.setId(map.getId());
        dto.setName(map.getName());
        dto.setDescription(map.getDescription());
        dto.setUserId(map.getUserId());
        dto.setImageData(map.getImageData());
        dto.setImageUrl(map.getImageUrl());

        // Convert Line entities to LineDTOs
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

    @GetMapping("/{name}")
    public ResponseEntity<Map> getMapByName(String name) {
        Map map = mapService.getMapByName(name);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map> getMapById(long id) {
        Map map = mapService.getMapById(id);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map> addMap(Map map) {
        Map newMap = mapService.addMap(map);
        return new ResponseEntity<>(newMap, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map> updateMap(Map map) {
        Map updatedMap = mapService.updateMap(map);
        return new ResponseEntity<>(updatedMap, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMap(long id) {
        mapService.deleteMap(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //New line saving idea.

    @PostMapping("/{mapId}/lines")
    public ResponseEntity<?> saveLines(@PathVariable("mapId") Long mapId, @RequestBody List<LineDTO> lines) {
        Map map = mapService.getMapById(mapId);
        if (map == null) return ResponseEntity.notFound().build();

        // Optional: Remove old lines for this map
        // lineRepository.deleteByMapId(mapId);

        List<Line> newLines = lines.stream().map(dto -> {
            Line l = new Line();
            l.setPoints(dto.getPoints());
            l.setColor(dto.getColor());
            l.setStrokeWidth(dto.getStrokeWidth());
            l.setMap(map);
            return l;
        }).collect(Collectors.toList());
        lineRepository.saveAll(newLines);

        return ResponseEntity.ok().build();
    }
    // Fetch lines for a map
    @GetMapping("/{mapId}/lines")
    public ResponseEntity<List<LineDTO>> getLines(@PathVariable Long mapId) {
        List<Line> lines = lineRepository.findByMapId(mapId);
        List<LineDTO> dtos = lines.stream().map(line -> {
            LineDTO dto = new LineDTO();
            dto.setPoints(line.getPoints());
            dto.setColor(line.getColor());
            dto.setStrokeWidth(line.getStrokeWidth());
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);

}
}

