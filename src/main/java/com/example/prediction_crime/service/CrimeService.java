package com.example.prediction_crime.service;

import com.example.prediction_crime.dto.CrimeDto;
import com.example.prediction_crime.model.Crime;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface CrimeService {

    List<CrimeDto> findAll();

    CrimeDto save(CrimeDto crimeDto) throws IOException;

    CrimeDto findById(Long id);

    CrimeDto findByType(String type);

    CrimeDto findByLieu(String lieu);

    CrimeDto findByDate(Instant date);

    CrimeDto update(CrimeDto crimeDto, 
    
    Long id) throws IOException;

    void delete(Long id);

}