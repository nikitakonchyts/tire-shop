package com.example.tire_warehouse.service;

import com.example.tire_warehouse.dto.TireDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TireService {

    TireDto save(TireDto tireDto);

    List<TireDto> getAll(int page, int size, String field);

    List<TireDto> findByBrand(String brand);

    List<TireDto> findByRadius(int radius);

    int getAmountTireByVendorCode(String vendorCode);

    TireDto findByVendorCode(String vendorCode);

    void removeSomeAmountTire(String vendorCode, int amount);

    void add();
}
