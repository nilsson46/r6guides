package com.example.r6guides.service;

import com.example.r6guides.models.MapGuide;
import com.example.r6guides.repository.MapGuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapGuideService {
    @Autowired
    private final MapGuideRepository mapGuideRepository;
    @Autowired
    public MapGuideService(MapGuideRepository mapGuideRepository) {
        this.mapGuideRepository = mapGuideRepository;
    }

    public MapGuideService saveMapGuide(MapGuideService mapGuide){
        return mapGuideRepository.save(mapGuide);
    }

    public List<MapGuide> getMapGuideByUser(Long userId){
        return mapGuideRepository.findByUserId(userId);
    }

    public List<MapGuide> getMapGuidesByMap(Long mapId){{
        return mapGuideRepository.findByMapId(mapId);
        }
    }
}
