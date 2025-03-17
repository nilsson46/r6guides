package com.example.r6guides.models;

import jakarta.persistence.*;

@Entity
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Floor name, 1F, 2F, 3F, B, Roof, etc.

    private String description; // Description of the floor, what is on it, etc.

    @ManyToOne
    @JoinColumn(name = "map_id", nullable = false)
    private Map map; // Map that the floor is on

    private String imageUrl; // Image of the floor layout



}
