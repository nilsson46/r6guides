package com.example.r6guides.controller;

import com.example.r6guides.DTO.MapDataDTO;
import com.example.r6guides.models.Map;
import com.example.r6guides.service.MapService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/maps/images")
public class UploadMapImageController {

    private final MapService mapService;

    public UploadMapImageController(MapService mapService) {
        this.mapService = mapService;
    }

    @PostMapping("/upload")
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
            mapService.addMap(map);

            return ResponseEntity.ok("Image uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Failed to upload image");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) {
        byte[] imageData = mapService.getImage(id);
        if (imageData != null) {
            return ResponseEntity.ok()
                    .header("Content-Type", "image/jpeg")
                    .body(imageData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}