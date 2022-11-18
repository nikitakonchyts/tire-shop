package com.example.tire_warehouse.repository;

import com.example.tire_warehouse.entity.Tire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TireRepository extends JpaRepository<Tire, Long> {

    List<Tire> findByBrand(String brand);

    List<Tire> findByRadius(int radius);

    int getAmountByBrand(String brand);

    Optional<Tire> findByVendorCode(String vendorCode);
}
