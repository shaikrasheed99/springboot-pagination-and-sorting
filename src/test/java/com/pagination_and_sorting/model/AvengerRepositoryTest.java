package com.pagination_and_sorting.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AvengerRepositoryTest {
    @Autowired
    private AvengerRepository avengerRepository;
    private Avenger ironman;

    @BeforeEach
    void setUp() {
        ironman = new Avenger("Ironman", "Iron Man", 2008);
        avengerRepository.save(ironman);
    }

    @Test
    void shouldBeAbleToSaveAvengerDetails() {
        Avenger savedIronman = avengerRepository.save(ironman);

        assertEquals(savedIronman.getId(), ironman.getId());
        assertEquals(savedIronman.getName(), ironman.getName());
        assertEquals(savedIronman.getIntroducedInMovie(), ironman.getIntroducedInMovie());
    }

    @Test
    void shouldBeAbleToGetAvengerDetailsById() {
        Avenger avenger = avengerRepository.findById(ironman.getId()).get();

        assertEquals(avenger.getId(), ironman.getId());
        assertEquals(avenger.getName(), ironman.getName());
        assertEquals(avenger.getIntroducedInYear(), ironman.getIntroducedInYear());
        assertEquals(avenger.getIntroducedInMovie(), ironman.getIntroducedInMovie());
    }
}
