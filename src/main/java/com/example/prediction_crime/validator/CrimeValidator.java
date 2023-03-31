package com.example.prediction_crime.validator;

import com.example.prediction_crime.dto.CrimeDto;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CrimeValidator {

    public static List<String> validate(CrimeDto crimeDto) {
        return getStrings(crimeDto == null, crimeDto.getType(), crimeDto.getLieu(), crimeDto.getNombre_victime(), crimeDto.getDate());
    }

    private static List<String> getStrings(boolean valid, String type, String lieu, Integer nombre_victime, Instant date) {
        List<String> errors = new ArrayList<>();

        if (valid) {
            errors.add("Veuillez renseigner le type'");
            errors.add("Veuillez renseigner le lieu'");
            errors.add("Veuillez renseigner le nombre de victime'");
            errors.add("Veuillez renseigner la date'");
            return errors;
        }
        if (!StringUtils.hasLength(type)) {
            errors.add("Veuillez renseigner le type'");
        }
        if (!StringUtils.hasLength(lieu)) {
            errors.add("Veuillez renseigner le lieu'");
        }
        if (!StringUtils.hasLength(String.valueOf(nombre_victime))) {
            errors.add("Veuillez renseigner le nombre de victime'");
        }
        if (!StringUtils.hasLength(String.valueOf(date))) {
            errors.add("Veuillez renseigner la date'");
        }

        return errors;
    }
    }


