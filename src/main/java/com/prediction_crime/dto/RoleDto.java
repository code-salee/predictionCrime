package com.prediction_crime.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Data
public class RoleDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull(message = "Role cannot be null")
    @NotBlank(message = "Le libellé ne peut pas être vide")
    private String libelle;
}
