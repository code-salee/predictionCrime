package com.prediction_crime.dto;

import com.prediction_crime.models.enums.RoleList;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class RoleDto implements Serializable {

    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private RoleList libelle;
}
