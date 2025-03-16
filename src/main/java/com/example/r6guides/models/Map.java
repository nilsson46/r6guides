package com.example.r6guides.models;

import jakarta.persistence.*;

@Entity
public class Map {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String imageUrl;
    @Column
    private String description;
   // private String objective;
   // private String spawn;
   // private String cameras;
   // private String destructibleWalls;
   // private String destructibleFloors;
   // private String destructibleCeilings;
   // private String mapGuide;
    //Image url?
}
