package com.example.discountcalculationservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TireResponse {

    private String brand;

    private int price;

    private String season;

    private int width;

    private int height;

    private int radius;

    private boolean spikes;
}
