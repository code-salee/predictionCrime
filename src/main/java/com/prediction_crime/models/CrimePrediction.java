package com.prediction_crime.models;

import com.prediction_crime.models.enums.CrimeSeverity;
import com.prediction_crime.models.enums.CrimeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "crime_predictions", indexes = {
        @Index(name = "idx_prediction_date", columnList = "predictionDate"),
        @Index(name = "idx_prediction_zone", columnList = "crime_zone_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrimePrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 10, scale = 8)
    private BigDecimal latitude;

    @Column(nullable = false, precision = 11, scale = 8)
    private BigDecimal longitude;

    @Column(nullable = false)
    private LocalDateTime predictionDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CrimeType predictedCrimeType;

    @Column(nullable = false, precision = 3, scale = 2)
    private BigDecimal crimeTypeProbability;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CrimeSeverity predictedSeverity;

    @Column(nullable = false, precision = 3, scale = 2)
    private BigDecimal severityConfidence;

    @Column(nullable = false, precision = 3, scale = 2)
    private BigDecimal highRiskProbability;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crime_zone_id")
    private CrimeZone crimeZone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    private District district;

    @Column(nullable = false)
    private String modelVersion;

    @Column(nullable = false)
    private Boolean isValidated = false;

    @Column
    private LocalDateTime actualCrimeDate;

    @Enumerated(EnumType.STRING)
    private CrimeType actualCrimeType;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
