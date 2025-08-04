package com.prediction_crime.repositories;

import com.prediction_crime.models.CrimeRecord;
import com.prediction_crime.models.District;
import com.prediction_crime.models.enums.CrimeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CrimeRecordRepository extends JpaRepository<CrimeRecord, Long> {

    List<CrimeRecord> findByDistrictAndCrimeDateBetween(
            District district,
            LocalDateTime startDate,
            LocalDateTime endDate
    );

    List<CrimeRecord> findByCrimeTypeAndCrimeDateAfter(
            CrimeType crimeType,
            LocalDateTime date
    );

    @Query("SELECT cr FROM CrimeRecord cr WHERE " +
            "cr.latitude BETWEEN :minLat AND :maxLat AND " +
            "cr.longitude BETWEEN :minLon AND :maxLon AND " +
            "cr.crimeDate BETWEEN :startDate AND :endDate")
    List<CrimeRecord> findCrimesInAreaAndTimeRange(
            @Param("minLat") BigDecimal minLatitude,
            @Param("maxLat") BigDecimal maxLatitude,
            @Param("minLon") BigDecimal minLongitude,
            @Param("maxLon") BigDecimal maxLongitude,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query("SELECT cr.crimeType, COUNT(cr) FROM CrimeRecord cr " +
            "WHERE cr.crimeDate >= :startDate " +
            "GROUP BY cr.crimeType " +
            "ORDER BY COUNT(cr) DESC")
    List<Object[]> getCrimeTypeStatistics(@Param("startDate") LocalDateTime startDate);

    @Query("SELECT EXTRACT(HOUR FROM cr.crimeDate), COUNT(cr) FROM CrimeRecord cr " +
            "WHERE cr.crimeDate >= :startDate " +
            "GROUP BY EXTRACT(HOUR FROM cr.crimeDate) " +
            "ORDER BY EXTRACT(HOUR FROM cr.crimeDate)")
    List<Object[]> getHourlyStatistics(@Param("startDate") LocalDateTime startDate);

    @Query("SELECT cr FROM CrimeRecord cr WHERE " +
            "(6371 * acos(cos(radians(:lat)) * cos(radians(cr.latitude)) * " +
            "cos(radians(cr.longitude) - radians(:lon)) + " +
            "sin(radians(:lat)) * sin(radians(cr.latitude)))) <= :radiusKm")
    List<CrimeRecord> findCrimesWithinRadius(
            @Param("lat") BigDecimal latitude,
            @Param("lon") BigDecimal longitude,
            @Param("radiusKm") Double radiusKm
    );
}
