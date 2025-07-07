package com.example.r6guides.DTO;

import lombok.Data;
import java.util.List;

@Data
public class MapWithImageAndLinesDTO {
    private Long id;
    private String name;
    private String description;
    private Long userId;
    private byte[] imageData;
    private String imageUrl;
    private List<LineDTO> lines;
}
