package com.example.prediction_crime.controller.api;

import com.example.prediction_crime.dto.CrimeDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Api("crime")
public interface CrimeApi {

    @GetMapping("/crime")
    List<CrimeDto> findAll();

    @PostMapping("/crime/create")
    CrimeDto save(@RequestBody CrimeDto crimeDto) throws IOException;

    @GetMapping("/crime/{id}")
    CrimeDto findById(@PathVariable Long id);

    @GetMapping("/crime/{type}")
    CrimeDto findByType(@PathVariable String type);

    @GetMapping("/crime/{lieu}")
    CrimeDto findByLieu(@PathVariable String lieu);

    @GetMapping("/crime/{date}")
    CrimeDto findByDate(@PathVariable Instant date);

    @PutMapping("/crime/{id}")
    CrimeDto put(@RequestBody CrimeDto crimeDto, @PathVariable Long id) throws IOException;

    @DeleteMapping("/crime/{id}")
    void delete(@PathVariable Long id);

}