package com.example.r6guides.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bombsite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(nullable = false)
    private String name; // Name of the bombsite, A, B,

    private String description; // Description of the bombsite, where it is, etc.

    @ManyToOne
    @JoinColumn(name = "map_id", nullable = false)
    private Map map; // Map that the bombsite is on

    @ManyToOne
    @JoinColumn(name = "floor_id", nullable = false)
    private Floor floor;

    private String imageUrl; // Image of the bombsite layout

    private String bombALocation; // Location of bomb A
    private String bombBLocation; // Location of bomb B

    @Version
    private Integer version;

}
