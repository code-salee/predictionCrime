package com.prediction_crime.models;

import com.prediction_crime.models.enums.CrimeSeverity;
import com.prediction_crime.models.enums.CrimeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "crime_records", indexes = {
        @Index(name = "idx_crime_location", columnList = "latitude, longitude"),
        @Index(name = "idx_crime_date", columnList = "crime_date"),
        @Index(name = "idx_crime_zone", columnList = "crime_zone_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrimeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String caseNumber;

    @Column(nullable = false, precision = 10, scale = 8)
    private BigDecimal latitude;

    @Column(nullable = false, precision = 11, scale = 8)
    private BigDecimal longitude;

    @Column(name = "crime_date", nullable = false)
    private LocalDateTime crimeDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CrimeType crimeType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CrimeSeverity severity;

    @Column(length = 1000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    private District district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crime_zone_id")
    private CrimeZone crimeZone;

    @Column(nullable = false)
    private Boolean isSolved = false;

    @Column
    private LocalDateTime solvedDate;

    @Column(length = 100)
    private String reportedBy;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Helper methods
    public int getHourOfDay() {
        return crimeDate.getHour();
    }

    public int getDayOfWeek() {
        return crimeDate.getDayOfWeek().getValue();
    }

    public int getMonth() {
        return crimeDate.getMonthValue();
    }

    public boolean isWeekend() {
        int dayOfWeek = getDayOfWeek();
        return dayOfWeek == 6 || dayOfWeek == 7; // Saturday or Sunday
    }

    public boolean isNightTime() {
        int hour = getHourOfDay();
        return hour >= 22 || hour <= 6;
    }
}
