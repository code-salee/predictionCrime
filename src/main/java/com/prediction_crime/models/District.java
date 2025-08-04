package com.prediction_crime.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

// District.java
@Entity
@Table(name = "districts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private Integer populationDensity;

    @Column(nullable = false)
    private BigDecimal averageIncome;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal unemploymentRate;

    @Column(nullable = false)
    private Integer numberOfPoliceStations;

    @Column(nullable = false)
    private Integer numberOfSchools;

    @Column(nullable = false)
    private Integer numberOfBars;

    @Column(nullable = false)
    private Integer numberOfParks;

    @Column(nullable = false, precision = 10, scale = 8)
    private BigDecimal centerLatitude;

    @Column(nullable = false, precision = 11, scale = 8)
    private BigDecimal centerLongitude;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CrimeRecord> crimeRecords;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}