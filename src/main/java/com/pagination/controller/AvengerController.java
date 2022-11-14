package com.pagination.controller;

import com.pagination.model.Avenger;
import com.pagination.model.AvengerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/avengers-with-pagination")
    public ResponseEntity<?> avengersWithPagination(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = true) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = true) int pageSize
    ) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Avenger> avengersPage = avengerRepository.findAll(pageRequest);

        List<Avenger> avengers = avengersPage.getContent();

        return ResponseEntity.status(HttpStatus.OK).body(avengers);
    }

    @GetMapping("/avengers-with-sorting")
    public ResponseEntity<?> avengersWithSorting(
            @RequestParam(value = "sort", defaultValue = "id, asc", required = true) String[] sortingParams
    ) {
        String field = sortingParams[0];
        String sortingDirection = sortingParams[1];

        Sort.Direction direction = sortingDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        List<Avenger> avengers = avengerRepository.findAll(Sort.by(direction, field));

        return ResponseEntity.status(HttpStatus.OK).body(avengers);
    }

    @GetMapping("/avengers-with-pagination-and-sorting")
    public ResponseEntity<?> avengersWithPaginationAndSorting(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = true) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = true) int pageSize,
            @RequestParam(value = "sort", defaultValue = "id, asc", required = true) String[] sortingParams
    ) {
        String field = sortingParams[0];
        String sortingDirection = sortingParams[1];
        Sort.Direction direction = sortingDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(direction, field));

        Page<Avenger> avengersPage = avengerRepository.findAll(pageRequest);

        List<Avenger> avengers = avengersPage.getContent();

        return ResponseEntity.status(HttpStatus.OK).body(avengers);
    }
}
