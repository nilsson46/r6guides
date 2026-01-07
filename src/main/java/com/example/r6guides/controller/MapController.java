package com.example.r6guides.controller;

import com.example.r6guides.DTO.MapWithImageAndLinesDTO;
import com.example.r6guides.models.Map;
import com.example.r6guides.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maps")
public class MapController {

    private final MapService mapService;

    @Autowired
    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping
    public ResponseEntity<List<Map>> getAllMaps() {
        List<Map> maps = mapService.getAllMaps();
        return new ResponseEntity<>(maps, HttpStatus.OK);
    }

    @GetMapping("/{id}/All")
    public ResponseEntity<MapWithImageAndLinesDTO> getMapWithImageAndLines(@PathVariable("id") Long id) {
        Map map = mapService.getMapById(id);
        if (map == null) {
            return ResponseEntity.notFound().build();
        }
        MapWithImageAndLinesDTO dto = mapService.convertToMapWithLinesDTO(map);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map> updateMap(@PathVariable("id") Long id, @RequestBody Map map) {
        Map existingMap = mapService.getMapById(id);
        if (existingMap == null) {
            return ResponseEntity.notFound().build();
        }
        map.setId(id);
        Map updatedMap = mapService.updateMap(map);
        return new ResponseEntity<>(updatedMap, HttpStatus.OK);
    }
}