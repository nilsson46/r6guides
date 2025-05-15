package com.example.r6guides.repository;

import com.example.r6guides.models.MapGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MapGuideRepository extends JpaRepository<MapGuide, Long> {
    // Custom query methods can be defined here if needed
    // For example, find by map name or user id
    // public List<MapGuide> findByMapName(String mapName);
    // public List<MapGuide> findByUserId(Long userId);

    List<MapGuide> findByMapId(Long mapId); // Find all map guides for a specific map
    List<MapGuide> findByUserId(Long userId); // Find all map guides created by a specific user
    List<MapGuide> findByFloorId(Long floorId); // Find all map guides for a specific floor

}
