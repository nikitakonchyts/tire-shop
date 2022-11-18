package com.example.discountcalculationservice.feign;

import com.example.discountcalculationservice.model.TireResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "tire-warehouse", url = "localhost:8081")
public interface TireWarehouseProxy {

    @GetMapping("/api/getByVendorCode")
    ResponseEntity<TireResponse> getByVendorCode(@RequestParam String vendorCode);
}
