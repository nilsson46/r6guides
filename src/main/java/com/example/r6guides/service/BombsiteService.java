package com.example.r6guides.service;

import com.example.r6guides.models.Bombsite;
import com.example.r6guides.repository.BombsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BombsiteService {

    private final BombsiteRepository bombsiteRepository;

    @Autowired
    public BombsiteService(BombsiteRepository bombsiteRepository) {
        this.bombsiteRepository = bombsiteRepository;
    }

    public List<Bombsite> getBombsitesByMapId(Long mapId) {
        return bombsiteRepository.findByMapId(mapId);
    }

    public List<Bombsite> getBombsitesByFloorId(Long floorId) {
        return bombsiteRepository.findByFloorId(floorId);
    }

    public Bombsite getBombsiteById(Long id) {
        return bombsiteRepository.findById(id).orElse(null);
    }

    public Bombsite saveBombsite(Bombsite bombsite) {
        return bombsiteRepository.save(bombsite);
    }

    public void deleteBombsite(Long id) {
        bombsiteRepository.deleteById(id);
    }
}
