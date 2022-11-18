package com.example.tire_warehouse.controller;

import com.example.tire_warehouse.dto.TireDto;
import com.example.tire_warehouse.service.TireService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/")
public class TireWarehouseController {

    private final TireService tireService;

    @GetMapping("/all")
    public ResponseEntity<List<TireDto>> getAll(@RequestParam int page, int size,
                                                @RequestParam(required = false) String field) {
       return new ResponseEntity<>(tireService.getAll(page, size, field), HttpStatus.OK);
    }

    @GetMapping("/getByBrand")
    public ResponseEntity<List<TireDto>> getByBrand(@RequestParam String brand) {
        return new ResponseEntity<>(tireService.findByBrand(brand), HttpStatus.OK);
    }

    @GetMapping("/getByRadius")
    public ResponseEntity<List<TireDto>> getByRadius(@RequestParam int radius) {
        return new ResponseEntity<>(tireService.findByRadius(radius), HttpStatus.OK);
    }

    @GetMapping("/getAmountByVendorCode")
    public ResponseEntity<Integer> getByRadius(@RequestParam String vendorCode) {
        return new ResponseEntity<>(tireService.getAmountTireByVendorCode(vendorCode), HttpStatus.OK);
    }

    @GetMapping("/getByVendorCode")
    public ResponseEntity<TireDto> getByVendorCode(@RequestParam String vendorCode) {
        return new ResponseEntity<>(tireService.findByVendorCode(vendorCode), HttpStatus.OK);
    }

    @PostMapping("/addNewTire")
    public ResponseEntity<TireDto> addNew(@RequestBody TireDto tireDto) {
        return new ResponseEntity<>(tireService.save(tireDto), HttpStatus.OK);
    }

    @PostMapping("/initialDataBase")
    public ResponseEntity<Void> initialDataBase() {
        tireService.add();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/removeSomeAmountTire")
    public ResponseEntity<Void> removeSomeAmount(@RequestParam String vendorCode, int amount) {
        tireService.removeSomeAmountTire(vendorCode, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
