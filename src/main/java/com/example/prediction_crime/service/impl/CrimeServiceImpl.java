package com.example.prediction_crime.service.impl;

import com.example.prediction_crime.dto.CrimeDto;
import com.example.prediction_crime.exceptions.EntityNotFoundException;
import com.example.prediction_crime.exceptions.ErrorCodes;
import com.example.prediction_crime.exceptions.InvalidEntityException;
import com.example.prediction_crime.model.Crime;
import com.example.prediction_crime.repository.CrimeRepository;
import com.example.prediction_crime.service.CrimeService;
import com.example.prediction_crime.validator.CrimeValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class CrimeServiceImpl implements CrimeService {

    private CrimeRepository crimeRepository;

    @Autowired
    public CrimeServiceImpl (CrimeRepository crimeRepository){this.crimeRepository = crimeRepository;}

    @Override
    public List<CrimeDto> findAll() {
        return null;
    }

    @Override
    public CrimeDto save(CrimeDto crimeDto) throws IOException {
        List<String> errors = CrimeValidator.validate(crimeDto);
        if(!errors.isEmpty()){
            log.error("Crime is not valid {}", crimeDto);
            throw new InvalidEntityException("Le crime n'est pas valide", ErrorCodes.CRIME_NOT_VALID, errors);
        }
        return CrimeDto.fromEntity(
                crimeRepository.save(CrimeDto.toEntity(crimeDto))
        );
    }

    @Override
    public CrimeDto findById(Long id) {
        if(id == null){
            log.error("Crime ID is null");
            return null;
        }
        return crimeRepository.findById(id)
                .map(CrimeDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune crime avec l'id " +id+ "n'a été trouver",
                        ErrorCodes.CRIME_NOT_FOUND
                ));
    }

    @Override
    public CrimeDto findByType(String type) {
        if(StringUtils.hasLength(type)){
            log.error("Crime type is null");
            return null;
        }
        return crimeRepository.findByType(type)
                .map(CrimeDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune crime avec le type " +type+ "n'a été trouver",
                        ErrorCodes.CRIME_NOT_FOUND
                ));
    }

    @Override
    public CrimeDto findByLieu(String lieu) {
        if(StringUtils.hasLength(lieu)){
            log.error("Crime place is null");
            return null;
        }
        return crimeRepository.findByLieu(lieu)
                .map(CrimeDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune crime avec le type " +lieu+ "n'a été trouver",
                        ErrorCodes.CRIME_NOT_FOUND
                ));
    }

    @Override
    public CrimeDto findByDate(Instant date) {
        if(date == null){
            log.error("Crime date is null");
            return null;
        }
        return crimeRepository.findByDate(date)
                .map(CrimeDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune crime avec la crime " +date+ "n'a été trouver",
                        ErrorCodes.CRIME_NOT_FOUND
                ));
    }

    @Override
    public CrimeDto update(CrimeDto crimeDto, Long id) throws IOException {
        if(id == null){
            log.error("Crime ID is null");
            return null;
        }

        else {
            Crime crime = crimeRepository.findById(id).orElseThrow(() ->
                    new EntityNotFoundException(
                            "Aucune crime avec l'ID = " + id + " ne se trouve dans la BDD",
                            ErrorCodes.CRIME_NOT_FOUND));
            if (!Objects.equals(crimeDto.getType(), ""))
                crime.setType(crimeDto.getType());
            if (!Objects.equals(crimeDto.getLieu(), ""))
                crime.setLieu(crimeDto.getLieu());
            if (!Objects.equals(crimeDto.getNombre_victime(), ""))
                crime.setNombre_victime(crimeDto.getNombre_victime());
            if (!Objects.equals(crimeDto.getDate(), ""))
                crime.setDate(crimeDto.getDate());
            crimeRepository.flush();
            return crimeDto.fromEntity(crime);
        }
    }

    @Override
    public void delete(Long id) {

    }
}
