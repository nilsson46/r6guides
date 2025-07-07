package com.example.r6guides.models;

import jakarta.persistence.*;
import lombok.*;
import com.example.r6guides.models.Line;
import java.util.List;

@Entity
@Table(name = "maps", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Map {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Long userId;
    @Lob
    private byte[] imageData;
    private String imageUrl;

    @OneToMany(mappedBy = "map", cascade = CascadeType.ALL)
    private List<Line> lines;


    // getters och setters
}
/*
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String imageUrl;
    @Column
    private String description;

    @Lob
    @Column(name = "image_data", columnDefinition = "LONGBLOB")
    private byte[] imageData;


    @Version
    private Integer version;
   // private String objective;
   // private String spawn;
   // private String cameras;
   // private String destructibleWalls;
   // private String destructibleFloors;
   // private String destructibleCeilings;
   // private String mapGuide;
    //Image url? */

