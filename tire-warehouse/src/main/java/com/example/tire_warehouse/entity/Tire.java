package com.example.tire_warehouse.entity;

import com.example.tire_warehouse.entity.enumEntity.Season;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tire {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "vendor_code")
    private String vendorCode;

    @Column(name = "price")
    private int price;

    @Column(name = "season")
    @Enumerated(EnumType.ORDINAL)
    private Season season;

    @Column(name = "width")
    private int width;

    @Column(name = "height")
    private int height;

    @Column(name = "radius")
    private int radius;

    @Column(name = "spikes")
    private boolean spikes;

    @Column(name = "amount")
    private int amount;
}
