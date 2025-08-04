package com.prediction_crime.controllers.api;

import com.prediction_crime.dto.CrimeDto;
//import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

//@Api("crime")
@RequestMapping("/crime")
public interface CrimeApi {

    @GetMapping("")
    List<CrimeDto> findAll();

    @PostMapping("/create")
    CrimeDto save(@RequestBody CrimeDto crimeDto) throws IOException;

    @GetMapping("/id/{id}")
    CrimeDto findById(@PathVariable Long id);

    @GetMapping("/type/{type}")
    CrimeDto findByType(@PathVariable String type);

    @GetMapping("/lieu/{lieu}")
    CrimeDto findByLieu(@PathVariable String lieu);

    @GetMapping("/date/{date}")
    CrimeDto findByDate(@PathVariable Instant date);

    @PutMapping("/id/{id}")
    CrimeDto put(@RequestBody CrimeDto crimeDto, @PathVariable Long id) throws IOException;

    @DeleteMapping("/id/{id}")
    void delete(@PathVariable Long id);

}