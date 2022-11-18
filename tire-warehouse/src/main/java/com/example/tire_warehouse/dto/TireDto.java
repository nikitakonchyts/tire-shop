package com.example.tire_warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TireDto {

    private String brand;
    private int price;
    private String season;
    private String vendorCode;
    private int width;
    private int height;
    private int radius;
    private boolean spikes;
    private int amount;
}
