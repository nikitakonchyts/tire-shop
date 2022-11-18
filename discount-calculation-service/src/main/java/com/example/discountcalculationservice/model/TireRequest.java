package com.example.discountcalculationservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TireRequest {

    private String brand;

    private String season;

    private int width;

    private int height;

    private int radius;

    private boolean spikes;

    private  int numberOfWheels;

    private int treadResidue;
}
