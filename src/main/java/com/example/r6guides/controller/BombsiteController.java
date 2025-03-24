package com.example.r6guides.controller;

import com.example.r6guides.models.Bombsite;
import com.example.r6guides.service.BombsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bombsite")
public class BombsiteController {

    private final BombsiteService bombsiteService;

    @Autowired
    public BombsiteController(BombsiteService bombsiteService) {
        this.bombsiteService = bombsiteService;
    }

    @GetMapping
    public ResponseEntity<List<Bombsite>> getAllBombsites() {
        List<Bombsite> bombsites = bombsiteService.getAllBombsites();
        return new ResponseEntity<>(bombsites, HttpStatus.OK);
    }
    @GetMapping("/map/{mapId}")
    public ResponseEntity<List<Bombsite>> getBombsitesByMapId(@PathVariable Long mapId) {
        List<Bombsite> bombsites = bombsiteService.getBombsitesByMapId(mapId);
        return new ResponseEntity<>(bombsites, HttpStatus.OK);
}

    @GetMapping("/floor/{floorId}")
    public ResponseEntity<Bombsite> getBombsitesByFloorId(@PathVariable Long floorId) {
        Bombsite bombsite = bombsiteService.getBombsiteById(floorId);
        return new ResponseEntity<>(bombsite, HttpStatus.OK);


    }

    @GetMapping("/{id}")
    public ResponseEntity<Bombsite> getBombsiteById(@PathVariable Long bombsiteId) {
        Bombsite bombsite = bombsiteService.getBombsiteById(bombsiteId);
        return new ResponseEntity<>(bombsite, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Bombsite> addBombsite(@RequestBody Bombsite bombsite) {
        Bombsite newBombsite = bombsiteService.saveBombsite(bombsite);
        return new ResponseEntity<>(newBombsite, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Bombsite> updateBombsite(@PathVariable Long id, @RequestBody Bombsite bombsite) {
        Bombsite existingBombsite = bombsiteService.getBombsiteById(id);
        if (existingBombsite != null) {
            bombsite.setId(id);
            Bombsite updatedBombsite = bombsiteService.saveBombsite(bombsite);
            return new ResponseEntity<>(updatedBombsite, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBombsite(@PathVariable Long id) {
        bombsiteService.deleteBombsite(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
