package com.prediction_crime.datafixtures;

import com.prediction_crime.models.Crime;
import com.prediction_crime.repositories.CrimeRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;

@AllArgsConstructor
@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
@Order(3)
public class CrimeFixtures implements CommandLineRunner {

    private CrimeRepository crimeRepository;

    @Override
    public void run(String... args) throws Exception {
        Date date = new Date();
        for (int i = 0; i < 5; i++) {
            crimeRepository.save(
                    new Crime("type"+(i+1), "lieu"+(i+1), i+1, date)
            );
        }
    }
}