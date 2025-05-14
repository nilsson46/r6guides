package com.example.r6guides.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    //@Column??
    private int failedLoginAttempts;

    //Unsure about this.
    //private boolean accountLocked;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

   // @Transient
   // private String roleType;

    public String getRoleType() {
        return role.getRoleType().name();
    }


}
