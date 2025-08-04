package com.prediction_crime.models;


import com.prediction_crime.models.enums.RiskLevel;
import com.prediction_crime.models.enums.RoleList;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleList libelle;
}
