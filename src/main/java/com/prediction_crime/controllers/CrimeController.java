package com.prediction_crime.controllers;

import com.prediction_crime.controllers.api.CrimeApi;
import com.prediction_crime.dto.CrimeDto;
import com.prediction_crime.services.CrimeService;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

@RestController
public class CrimeController implements CrimeApi {

    CrimeService service;

    @Override
    public List<CrimeDto> findAll() {
        return service.findAll();
    }

    @Override
    public CrimeDto save(CrimeDto crimeDto) throws IOException {
        return service.save(crimeDto);
    }

    @Override
    public CrimeDto findById(Long id) {
        return service.findById(id);
    }

    @Override
    public CrimeDto findByType(String type) {
        return service.findByType(type);
    }

    @Override
    public CrimeDto findByLieu(String lieu) {
        return service.findByLieu(lieu);
    }

    @Override
    public CrimeDto findByDate(Instant date) {
        return service.findByDate(date);
    }

    @Override
    public CrimeDto put(CrimeDto crimeDto, Long id) throws IOException {
        return service.update(crimeDto, id);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

}
