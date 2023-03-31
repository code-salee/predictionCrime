package com.example.prediction_crime.model;

import javax.persistence.*;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import java.time.Instant;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper= false)
@Entity
@Table(name="crime")
public class Crime {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   
    @Column(name = "type")
    private String type;

    @Column(name = "lieu")
    private String lieu;

    @Column(name = "nombre_victime")
    private Integer nombre_victime;

    @CreatedDate
    @Column(name="date")
    private Instant date;

    public Crime(String type, String lieu, int nombre_victime, Date date) {
    }
}