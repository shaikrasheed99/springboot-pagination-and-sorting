package com.pagination.controller;

import com.pagination.model.Avenger;
import com.pagination.model.AvengerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AvengerController.class)
public class AvengerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AvengerRepository avengerRepository;
    private List<Avenger> avengers;
    private PageRequest pageRequest;
    private Page<Avenger> avengersPage;

    @BeforeEach
    void setUp() {
        Avenger ironman = new Avenger("Ironman", "Iron Man", 2008);
        Avenger thor = new Avenger("Thor", "Thor", 2011);
        avengers = Arrays.asList(ironman, thor);
        pageRequest = PageRequest.of(0, 1);
        avengersPage = new PageImpl<>(avengers);
    }

    @Test
    void shouldBeAbleToReturnListOfAvengers() throws Exception {
        when(avengerRepository.findAll()).thenReturn(avengers);

        ResultActions result = mockMvc.perform(get("/avengers"));

        result.andExpect(status().isOk()).andDo(print());

        verify(avengerRepository, times(1)).findAll();
    }

    @Test
    void shouldBeAbleToReturnListOfAvengersWithPagination() throws Exception {
        when(avengerRepository.findAll(pageRequest)).thenReturn(avengersPage);

        ResultActions result = mockMvc.perform(get("/avengers-with-pagination?pageNumber=0&pageSize=1"));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(avengers.get(0).getId()))
                .andExpect(jsonPath("$.[0].name").value(avengers.get(0).getName()))
                .andExpect(jsonPath("$.[0].introducedInMovie").value(avengers.get(0).getIntroducedInMovie()))
                .andExpect(jsonPath("$.[1].id").value(avengers.get(1).getId()))
                .andExpect(jsonPath("$.[1].name").value(avengers.get(1).getName()))
                .andExpect(jsonPath("$.[1].introducedInYear").value(avengers.get(1).getIntroducedInYear()))
                .andDo(print());

        verify(avengerRepository, times(1)).findAll(pageRequest);
    }

    @Test
    void shouldBeAbleToReturnListOfAvengersWithSorting() throws Exception {
        when(avengerRepository.findAll(Sort.by(Sort.Direction.DESC, "name"))).thenReturn(avengers);

        ResultActions result = mockMvc.perform(get("/avengers-with-sorting?sort=name,desc"));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(avengers.get(0).getId()))
                .andExpect(jsonPath("$.[0].name").value(avengers.get(0).getName()))
                .andExpect(jsonPath("$.[0].introducedInMovie").value(avengers.get(0).getIntroducedInMovie()))
                .andExpect(jsonPath("$.[1].id").value(avengers.get(1).getId()))
                .andExpect(jsonPath("$.[1].name").value(avengers.get(1).getName()))
                .andExpect(jsonPath("$.[1].introducedInYear").value(avengers.get(1).getIntroducedInYear()))
                .andDo(print());

        verify(avengerRepository, times(1)).findAll(Sort.by(Sort.Direction.DESC, "name"));
    }
}
