package com.pagination.controller;

import com.pagination.model.Avenger;
import com.pagination.model.AvengerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AvengerController {
    private final AvengerRepository avengerRepository;

    public AvengerController(AvengerRepository avengerRepository) {
        this.avengerRepository = avengerRepository;
    }

    @GetMapping("/avengers")
    public ResponseEntity<?> avengers() {
        List<Avenger> avengers = avengerRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(avengers);
    }
}
