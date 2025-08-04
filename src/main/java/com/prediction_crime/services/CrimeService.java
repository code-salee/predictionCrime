package com.prediction_crime.services;

import com.prediction_crime.dto.CrimeDto;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

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