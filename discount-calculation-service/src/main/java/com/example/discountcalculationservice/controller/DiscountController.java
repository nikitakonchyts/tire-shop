package com.example.discountcalculationservice.controller;

import com.example.discountcalculationservice.model.TireRequest;
import com.example.discountcalculationservice.service.DiscountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class DiscountController {

    private final DiscountService discountService;

    @PostMapping(value = "/discount")
    public ResponseEntity<Double> discountCalculation(@RequestBody TireRequest tire) {
        return new ResponseEntity<>(discountService.discountCalculation(tire), HttpStatus.OK);
    }
}
