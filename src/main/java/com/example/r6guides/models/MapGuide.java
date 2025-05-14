package com.example.r6guides.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "map_guides")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapGuide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // User that created the map guide

    @Column(nullable = false)
    private String name; // Name of the map guide

    @Column(nullable = false)
    private String description; // Description of the map guide

    @ManyToOne
    @JoinColumn(name = "map_id", nullable = false)
    private Map map; // Map that the guide is for

    @ManyToOne
    @JoinColumn(name = "floor_id", nullable = false)
    private Floor floor; // Floor that the guide is for

    private String imageUrl; // Image of the map guide


}
