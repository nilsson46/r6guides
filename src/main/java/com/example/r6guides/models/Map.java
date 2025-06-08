package com.example.r6guides.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    //Image url?
}
