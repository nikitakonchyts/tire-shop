package com.example.tire_warehouse.service.impl;

import com.example.tire_warehouse.dto.TireDto;
import com.example.tire_warehouse.dto.converter.TireMapper;
import com.example.tire_warehouse.entity.Tire;
import com.example.tire_warehouse.entity.enumEntity.Season;
import com.example.tire_warehouse.exception.NotFoundByVendorCodeException;
import com.example.tire_warehouse.repository.TireRepository;
import com.example.tire_warehouse.service.TireService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Service
public class TireServiceImpl implements TireService {

    private final TireRepository tireRepository;
    private final TireMapper tireMapper;

    @Override
    public TireDto save(TireDto tireDto) {
        Tire tire = tireMapper.convertToEntity(tireDto);
        if (tire.getVendorCode() == null) {
            tire.setVendorCode(tire.getBrand().substring(0, 3)
                    + tire.getRadius()
                    + tire.getHeight()
                    + tire.getWidth()
                    + tire.getSeason().toString().substring(0, 3)
                    + (tire.isSpikes() ? 1 : 0));
        }
        return tireMapper.convertToDto(tireRepository.save(tire));
    }

    @Override
    public List<TireDto> getAll(int page, int size, String field) {
        Pageable pageable;
        if (field != null) {
            pageable = PageRequest.of(page - 1, size, Sort.by(field));
        } else {
            pageable = PageRequest.of(page - 1, size);
        }
        List<Tire> tireList = new ArrayList<>();
        for (Tire tire : tireRepository.findAll(pageable)) {
            tireList.add(tire);
        }
        return convertList(tireList);
    }

    @Override
    public List<TireDto> findByBrand(String brand) {
        return convertList(tireRepository.findByBrand(brand));
    }

    @Override
    public List<TireDto> findByRadius(int radius) {
        return convertList(tireRepository.findByRadius(radius));
    }

    @Override
    public int getAmountTireByVendorCode(String vendorCode) {
        return findByVendorCode(vendorCode.trim()).getAmount();
    }

    @Override
    public TireDto findByVendorCode(String vendorCode) {
        return tireMapper.convertToDto(tireRepository.findByVendorCode(vendorCode.trim()).orElseThrow(
                () -> new NotFoundByVendorCodeException("Не найдено шин по такому артиклу")
        ));
    }

    @Override
    public void removeSomeAmountTire(String vendorCode, int amount) {
        Tire tire = tireRepository.findByVendorCode(vendorCode.trim()).orElseThrow(
                () -> new NotFoundByVendorCodeException("Не найдено шин по такому артиклу")
        );
        int resultAmount = tire.getAmount() - amount;
        if (resultAmount >= 0) {
            tire.setAmount(resultAmount);
        } else {
            throw new RuntimeException("Нет такого количества шин");
        }
        tireRepository.save(tire);
    }

    private List<TireDto> convertList(List<Tire> tireList) {
        List<TireDto> result = new ArrayList<>();
        for (Tire tire : tireList) {
            result.add(tireMapper.convertToDto(tire));
        }
        return result;
    }

    @Override
    public void add() {
        List<String> brands = Arrays.asList("Michelin", "Dunlop", "Continental", "Continental", "Nokian");
        List<Integer> heights = Arrays.asList(165, 175, 185, 195, 205);
        List<Integer> widths = Arrays.asList(55, 60, 65, 70, 80);
        List<Integer> prices = Arrays.asList(100, 120, 140, 160, 180);
        List<Integer> radius = Arrays.asList(13, 14, 15, 16, 17);
        Random random = new Random();
        for (Season season : Season.values()) {
            for (String brand : brands) {
                for (Integer height : heights) {
                    for (Integer width : widths) {
                        for (Integer rad : radius) {
                            Tire tire = new Tire();
                            tire.setBrand(brand);
                            tire.setHeight(height);
                            tire.setWidth(width);
                            tire.setRadius(rad);
                            tire.setPrice(prices.get(random.nextInt(5)));
                            tire.setAmount(40);
                            tire.setSeason(season);
                            if (season.equals(Season.WINTER)) {
                                tire.setSpikes(random.nextBoolean());
                            } else {
                                tire.setSpikes(false);
                            }
                            save(tireMapper.convertToDto(tire));
                        }
                    }
                }
            }
        }
    }
}
