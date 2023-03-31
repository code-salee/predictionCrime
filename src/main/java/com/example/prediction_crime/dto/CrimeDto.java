package com.example.prediction_crime.dto;

import com.example.prediction_crime.model.Crime;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;

@Data
@Builder
public class CrimeDto {
    private Integer id;
    private String type;
    private String lieu;
    private Integer nombre_victime;
    private Instant date;
    //Mapping entité dto: fromEntity permet de mapper une relation de l'entité (Category) vers dto (CategoryDto)
    public static CrimeDto fromEntity (Crime crime) {
        if(crime == null){
            return null;
        }
        return CrimeDto.builder()
                .id(crime.getId())
                .type(crime.getType())
                .lieu(crime.getLieu())
                .nombre_victime(crime.getNombre_victime())
                .date(crime.getDate())
                .build();
    }
    //Mapping entité dto: toEntity permet ude mapper ne relation de dto vers l'entité
    public static Crime toEntity (CrimeDto crimeDto){
        if(crimeDto== null){
            return null;
        }
        Crime crime = new Crime();
        crime.setId(crimeDto.getId());
        crime.setType(crimeDto.getType());
        crime.setLieu(crimeDto.getLieu());
        crime.setNombre_victime(crimeDto.getNombre_victime());
        crime.setDate(crimeDto.getDate());
        return crime;
    }
}