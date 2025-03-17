package com.example.r6guides.repository;

import com.example.r6guides.models.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MapRepository extends JpaRepository<Map, Long> {
    Map findByName(String name);
    Map findById(long id);
    List<Map> findAllByNameContaining(String name);
    //List<Map> findByInRankedPool(boolean inRankedPool);
}
