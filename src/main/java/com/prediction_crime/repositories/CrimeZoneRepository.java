package com.prediction_crime.repositories;

import com.prediction_crime.models.CrimeZone;
import com.prediction_crime.models.enums.RiskLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CrimeZoneRepository extends JpaRepository<CrimeZone, Long> {

    Optional<CrimeZone> findByZoneName(String zoneName);

    List<CrimeZone> findByRiskLevel(RiskLevel riskLevel);

    @Query("SELECT cz FROM CrimeZone cz WHERE " +
            "(6371000 * acos(cos(radians(:lat)) * cos(radians(cz.centerLatitude)) * " +
            "cos(radians(cz.centerLongitude) - radians(:lon)) + " +
            "sin(radians(:lat)) * sin(radians(cz.centerLatitude)))) <= cz.radius")
    List<CrimeZone> findZonesContainingPoint(
            @Param("lat") BigDecimal latitude,
            @Param("lon") BigDecimal longitude
    );

    @Query("SELECT cz FROM CrimeZone cz ORDER BY cz.totalCrimes DESC")
    List<CrimeZone> findZonesByTotalCrimesDesc();
}
