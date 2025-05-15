package com.example.r6guides.controller;

import com.example.r6guides.models.MapGuide;
import com.example.r6guides.service.MapGuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/map-guides")
public class MapGuideController {

    @Autowired
    private MapGuideService mapGuideService;

    @PostMapping
    public ResponseEntity<MapGuide> saveMapGuide(@RequestBody MapGuide mapGuide){
        MapGuide savedMapguide = mapGuideService.saveMapGuide(mapGuide);
        return ResponseEntity.ok(savedMapguide);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MapGuide>> getMapGuideByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(mapGuideService.getMapGuideByUser(userId));
    }

    @GetMapping("/map/{mapId}")
    public ResponseEntity<List<MapGuide>> getMapGuidesByMap(@PathVariable Long mapId){
        return ResponseEntity.ok(mapGuideService.getMapGuidesByMap(mapId));
    }
}
