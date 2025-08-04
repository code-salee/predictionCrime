package com.prediction_crime.repositories;

import com.prediction_crime.models.Crime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface CrimeRepository extends JpaRepository<Crime, Long> {

    List<Crime> findAll();

    Optional<Crime> findById(Long id);

    Optional<Crime> findByType(String type);

    Optional<Crime> findByLieu(String lieu);

    Optional<Crime> findByDate(Instant date);

    //Optional<Crime> nbrevictime(Integer nombre_victime);

}
