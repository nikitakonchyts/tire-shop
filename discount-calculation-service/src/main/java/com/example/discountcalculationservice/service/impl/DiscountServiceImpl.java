package com.example.discountcalculationservice.service.impl;

import com.example.discountcalculationservice.model.TireRequest;
import com.example.discountcalculationservice.model.TireResponse;
import com.example.discountcalculationservice.feign.TireWarehouseProxy;
import com.example.discountcalculationservice.service.DiscountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DiscountServiceImpl implements DiscountService {

    private final TireWarehouseProxy proxy;

    @Override
    public double discountCalculation(TireRequest tire) {
        if (tire.getTreadResidue() < 5) {
            throw new RuntimeException("Покрышка не пригодна для продажи");
        }
        TireResponse tireFromWarehouse = proxy.getByVendorCode(code(tire)).getBody();
        if (tireFromWarehouse == null) {
            throw new RuntimeException("Нет покрышки с таким артиклом");
        }
        int tirePrice = tireFromWarehouse.getPrice();
        double residualValue = 1 - (double) (10 - tire.getTreadResidue() / 6);
        double discountResult = tirePrice * residualValue * 0.65;
        if (tire.getNumberOfWheels() == 0) {
            // скидка с одного колеса
            return discountResult;
        } else {
            // цена колёс со скидкой
            return tirePrice * tire.getNumberOfWheels() - discountResult * tire.getNumberOfWheels();
        }
    }

    private String code(TireRequest tire) {
        return tire.getBrand().substring(0, 3)
                + tire.getRadius()
                + tire.getHeight()
                + tire.getWidth()
                + tire.getSeason().toString().substring(0, 3)
                + (tire.isSpikes() ? 1 : 0);
    }
}
