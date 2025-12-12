package com.example.r6guides.controller;

import com.example.r6guides.DTO.LineDTO;
import com.example.r6guides.models.Line;
import com.example.r6guides.models.Map;
import com.example.r6guides.repository.LineRepository;
import com.example.r6guides.service.MapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lines")
//TODO: implement line controller with endpoints for line service and models and take the code from Mapcontroller
public class LineController {
    private final MapService mapService;
    private final LineRepository lineRepository;

    public LineController(MapService mapService, LineRepository lineRepository) {
        this.mapService = mapService;
        this.lineRepository = lineRepository;
    }

    @PostMapping("/{mapId}/lines")
    public ResponseEntity<String> saveLines(@PathVariable("mapId") Long mapId, @RequestBody List<LineDTO> lineDTOs) {
        Map map = mapService.getMapById(mapId);
        if (map == null) {
            return ResponseEntity.notFound().build();
        }

        List<Line> lines = lineDTOs.stream().map(dto -> {
            Line line = new Line();
            if (dto.getId() != null) {
                line = lineRepository.findById(dto.getId()).orElse(new Line());
            }
            line.setPoints(dto.getPoints());
            line.setColor(dto.getColor());
            line.setStrokeWidth(dto.getStrokeWidth());
            line.setMap(map);
            return line;
        }).collect(Collectors.toList());

        lineRepository.saveAll(lines);
        return ResponseEntity.ok("Lines saved successfully");
    }

    @GetMapping("/{mapId}/lines")
    public ResponseEntity<List<LineDTO>> getLines(@PathVariable("mapId") Long mapId) {
        List<Line> lines = lineRepository.findByMapId(mapId);
        List<LineDTO> lineDTOs = lines.stream().map(line -> {
            LineDTO dto = new LineDTO();
            dto.setId(line.getId());
            dto.setPoints(line.getPoints());
            dto.setColor(line.getColor());
            dto.setStrokeWidth(line.getStrokeWidth());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(lineDTOs);
    }

    @DeleteMapping("/lines/{lineId}")
    public ResponseEntity<String> deleteLine(@PathVariable("lineId") Long lineId) {
        if (lineRepository.existsById(lineId)) {
            lineRepository.deleteById(lineId);
            return ResponseEntity.ok("Line deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
