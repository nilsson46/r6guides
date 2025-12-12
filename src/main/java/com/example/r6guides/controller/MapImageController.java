package com.example.r6guides.controller;

import com.example.r6guides.DTO.MapDataDTO;
import com.example.r6guides.models.Map;
import com.example.r6guides.service.MapService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

//Move some code here later add endpoints for map images service and models
@RestController
@RequestMapping("/api/map-images")

public class MapImageController {

    private final MapService mapService;

    public MapImageController(com.example.r6guides.service.MapService mapService) {
        this.mapService = mapService;
    }

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

            map = mapService.addMap(map);

            return ResponseEntity.ok("Image and data uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Failed to upload image and data");
        }
    }

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
}
