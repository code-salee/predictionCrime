package com.prediction_crime.repositories;

import com.prediction_crime.models.CrimePrediction;
import com.prediction_crime.models.CrimeZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CrimePredictionRepository extends JpaRepository<CrimePrediction, Long> {

    List<CrimePrediction> findByPredictionDateBetween(
            LocalDateTime startDate,
            LocalDateTime endDate
    );

    List<CrimePrediction> findByCrimeZoneAndPredictionDateAfter(
            CrimeZone crimeZone,
            LocalDateTime date
    );

    @Query("SELECT cp FROM CrimePrediction cp WHERE " +
            "cp.highRiskProbability >= :threshold AND " +
            "cp.predictionDate BETWEEN :startDate AND :endDate")
    List<CrimePrediction> findHighRiskPredictions(
            @Param("threshold") BigDecimal threshold,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query("SELECT AVG(CASE WHEN cp.predictedCrimeType = cp.actualCrimeType THEN 1.0 ELSE 0.0 END) " +
            "FROM CrimePrediction cp WHERE cp.isValidated = true")
    BigDecimal calculateModelAccuracy();
}