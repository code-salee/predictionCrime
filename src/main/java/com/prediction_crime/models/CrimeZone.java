package com.prediction_crime.models;

import com.prediction_crime.models.enums.RiskLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "crime_zones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrimeZone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String zoneName;

    @Column(nullable = false, precision = 10, scale = 8)
    private BigDecimal centerLatitude;

    @Column(nullable = false, precision = 11, scale = 8)
    private BigDecimal centerLongitude;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal radius; // in meters

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RiskLevel riskLevel;

    @Column(nullable = false)
    private Integer totalCrimes = 0;

    @Column(nullable = false, precision = 3, scale = 2)
    private BigDecimal averageSeverity = BigDecimal.ZERO;

    @Column(nullable = false)
    private Integer clusterGroup;

    @OneToMany(mappedBy = "crimeZone", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CrimeRecord> crimeRecords;

    @OneToMany(mappedBy = "crimeZone", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CrimePrediction> predictions;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}