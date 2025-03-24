package com.example.r6guides.repository;

import com.example.r6guides.models.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {
    List<Floor> findByMapId(Long mapId);
    List<Floor> findByMapIdOrderByName(Long mapId);
    //List<Floor> findByMapIdOrderByLevel(Long mapId);
    //List<Floor> findByInRankedPool(boolean inRankedPool);
}
