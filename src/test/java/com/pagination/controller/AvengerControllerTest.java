package com.pagination.controller;

import com.pagination.model.Avenger;
import com.pagination.model.AvengerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AvengerController.class)
public class AvengerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AvengerRepository avengerRepository;
    private List<Avenger> avengers;

    @BeforeEach
    void setUp() {
        Avenger ironman = new Avenger("Ironman", "Iron Man", 2008);
        Avenger thor = new Avenger("Thor", "Thor", 2011);
        avengers = Arrays.asList(ironman, thor);
    }

    @Test
    void shouldBeAbleToReturnListOfAvengers() throws Exception {
        when(avengerRepository.findAll()).thenReturn(avengers);

        ResultActions result = mockMvc.perform(get("/avengers"));

        result.andExpect(status().isOk()).andDo(print());

        verify(avengerRepository, times(1)).findAll();
    }
}
