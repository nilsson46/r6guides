package com.example.r6guides.DTO;

import lombok.Data;

import java.util.List;
@Data
public class LineDTO {
    private Long id;
    private List<Double> points;
    private String color;
    private int strokeWidth;
}

