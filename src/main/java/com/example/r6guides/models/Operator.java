package com.example.r6guides.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Entity
@Table(name = "operators", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Name is required")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OperatorType type;

    @Column(nullable = false)
    @NotBlank(message = "Organization is required")
    private String organization;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    @NotBlank(message = "Primary weapon is required")
    private String primaryWeapon;

    @Column(nullable = false)
    @NotBlank(message = "Secondary weapon is required")
    private String secondaryWeapon;

    @Column(nullable = false)
    @NotBlank(message = "Gadget is required")
    private String gadget;

    @Column(nullable = false)
    @NotBlank(message = "Ability is required")
    private String ability;

    @Column
    @Pattern(regexp = "^(http|https)://.*$", message = "Invalid URL format")
    private String imageUrl;

    @Column
    @Pattern(regexp = "^(http|https)://.*$", message = "Invalid URL format")
    private String iconUrl;

    public enum OperatorType {
        ATTACKER, DEFENDER
    }
}

