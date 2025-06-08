package com.example.r6guides.controller;

import com.example.r6guides.models.Map;
import com.example.r6guides.repository.MapRepository;
import com.example.r6guides.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/maps")
public class MapController {

    private final MapService mapService;
    private final MapRepository mapRepository;
    @Autowired
    public MapController(MapService mapService, MapRepository mapRepository) {
        this.mapService = mapService;
        this.mapRepository = mapRepository;
    }

    @GetMapping
    public ResponseEntity<List<Map>> getAllMaps() {
        List<Map> maps = mapService.getAllMaps();
        return new ResponseEntity<>(maps, HttpStatus.OK);
    }

    @PostMapping("/{id}/upload-image")
    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            mapService.saveImage(id, file.getBytes());
            return ResponseEntity.ok("Image uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Failed to upload image");
        }
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        byte[] imageData = mapService.getImage(id);
        if (imageData != null) {
            return ResponseEntity.ok()
                    .header("Content-Type", "image/jpeg") // Adjust based on your image format
                    .body(imageData);
        } else {
            return ResponseEntity.notFound().build();
        }
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
}
