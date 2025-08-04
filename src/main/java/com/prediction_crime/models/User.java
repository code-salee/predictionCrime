
package com.prediction_crime.models;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String matricule;

    @Column(nullable = false)
    private String password;

    private String fullName;

    private String role; // ex: ADMIN, AGENT, ANALYST

    // Autres champs utiles selon ton besoin
}
