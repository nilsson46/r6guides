package com.example.r6guides.controller;

import com.example.r6guides.models.Bombsite;
import com.example.r6guides.models.Floor;
import com.example.r6guides.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/floor")
public class FloorController {
    private final FloorService floorService;

    @Autowired
    public FloorController(FloorService floorService) {
        this.floorService = floorService;
    }

    @GetMapping("/map/{mapId}")
    public ResponseEntity<List<Floor>> getFloorsByMapId(@PathVariable Long mapId) {
        List<Floor> floors = floorService.getFloorsByMapId(mapId);
        return new ResponseEntity<>(floors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Floor> getFloorById(@PathVariable Long floorId) {
        Floor floor = floorService.getFloorById(floorId);
        return new ResponseEntity<>(floor, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Floor> addFloor(@RequestBody Floor floor) {
        Floor newFloor = floorService.addFloor(floor);
        return new ResponseEntity<>(newFloor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Floor> updateFloor(@RequestBody Floor floor) {
        Floor updatedFloor = floorService.updateFloor(floor);
        return new ResponseEntity<>(updatedFloor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFloor(@PathVariable Long id) {
        floorService.deleteFloor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
