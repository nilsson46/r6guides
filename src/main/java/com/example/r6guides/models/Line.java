package com.example.r6guides.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
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
    @JsonBackReference
    private Map map;
    // getters och setters

   /* @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; */
}