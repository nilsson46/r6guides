package com.example.r6guides.service;

import com.example.r6guides.models.Map;
import com.example.r6guides.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapService {
    private final MapRepository mapRepository;

    @Autowired
    public MapService(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }

    public List<Map> getAllMaps() {
        return mapRepository.findAll();
    }

    public Map getMapById(long id) {
        return mapRepository.findById(id);
    }

    public Map getMapByName(String name) {
        return mapRepository.findByName(name);
    }

    public List<Map> getMapsByName(String name) {
        return mapRepository.findAllByNameContaining(name);
    }

    public Map addMap(Map map) {
        return mapRepository.save(map);
    }

    public void deleteMap(long id) {
        mapRepository.deleteById(id);
    }

    public Map updateMap(Map map) {
        return mapRepository.save(map);
    }

    public void saveImage(Long id, byte[] imageData) {
        Map map = mapRepository.findById(id).orElseThrow(() -> new RuntimeException("Map not found"));
        map.setImageData(imageData);
        mapRepository.save(map);
    }

    public byte[] getImage(Long id) {
        Map map = mapRepository.findById(id).orElseThrow(() -> new RuntimeException("Map not found"));
        return map.getImageData();
    }

}
