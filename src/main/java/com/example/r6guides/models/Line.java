package com.example.r6guides.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<Double> points;
    private String color;
    private int strokeWidth;

    @ManyToOne
    @JoinColumn(name = "map_id")
    private Map map;
    // getters och setters
}