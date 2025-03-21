package com.example.r6guides.service;

import com.example.r6guides.models.Floor;
import com.example.r6guides.repository.FloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloorService {
    private final FloorRepository floorRepository;

    @Autowired
    public FloorService(FloorRepository floorRepository) {
        this.floorRepository = floorRepository;
    }

    public List<Floor>getFloorsByMapId(Long mapId) {
        return floorRepository.findByMapId(mapId);
    }

    public Floor getFloorById(Long id) {
        return floorRepository.findById(id).orElse(null);
    }

    public Floor addFloor(Floor floor) {
        return floorRepository.save(floor);
    }

    public void deleteFloor(Long id) {
        floorRepository.deleteById(id);
    }

    public Floor updateFloor(Floor floor) {
        return floorRepository.save(floor);
    }

    public List<Floor> getAllFloors() {return floorRepository.findAll();
    }
}
