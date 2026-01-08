package com.example.r6guides.repository;

import com.example.r6guides.models.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineRepository extends JpaRepository<Line, Long> {

    List<Line> findByMapId(Long mapId);

}