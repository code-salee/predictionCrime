package com.example.prediction_crime.controller;

import com.example.prediction_crime.controller.api.CrimeApi;
import com.example.prediction_crime.dto.CrimeDto;
import com.example.prediction_crime.service.CrimeService;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

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
