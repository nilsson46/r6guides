package com.example.r6guides.repository;

import com.example.r6guides.models.Bombsite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BombsiteRepo extends JpaRepository<Bombsite, Long> {
    List<Bombsite> findByMapId(Long mapId);
    List<Bombsite> findByFloorId(Long floorId);
    List<Bombsite> findByMapIdAndObjectiveType(Long mapId, String objectiveType);
}
