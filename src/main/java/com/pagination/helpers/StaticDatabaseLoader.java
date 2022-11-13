package com.pagination.helpers;

import com.pagination.model.Avenger;
import com.pagination.model.AvengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class StaticDatabaseLoader {
    @Autowired
    private AvengerRepository avengerRepository;

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            String[] names = {"Ironman", "Thanos", "Captain America", "Thor", "Loki", "Hulk", "Captain Marvel", "Spider Man", "Black Widow", "Hawkeye", "Black Panther", "Doctor Strange", "Ant Man", "Vision", "Hulkbuster", "War Machine", "Nick Fury", "Falcon", "Winter Soldier", "Ultron", "Red Skull" };
            String[] movies = {"Iron Man", "The Avengers", "Captain America: The First Avenge", "Thor", "Thor", "The Incredible Hulk", "Captain Marvel", "Captain America: Civil War", "Iron Man 2", "Thor", "Captain America: Civil War", "Doctor Strange", "Ant Man", "Avengers: Age of Ultron", "Avengers: Age of Ultron", "Iron Man", "Iron Man", "Captain America: The Winter Soldier", "Captain America: The Winter Soldier", "Avengers: Age of Ultron", "Captain America: The First Avenger" };
            int[] years = {2008, 2012, 2011, 2011, 2011, 2008, 2019, 2016, 2010, 2015, 2016, 2016, 2015, 2015, 2015, 2008, 2008, 2014, 2014, 2015, 2011};

            ArrayList<Avenger> avengers = new ArrayList<>();

            for (int i = 0; i < 21; i++) {
                avengers.add(new Avenger(names[i], movies[i], years[i]));
            }

            avengerRepository.saveAll(avengers);
        };
    }
}
