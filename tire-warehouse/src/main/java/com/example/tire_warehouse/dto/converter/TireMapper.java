package com.example.tire_warehouse.dto.converter;

import com.example.tire_warehouse.dto.TireDto;
import com.example.tire_warehouse.dto.uses.SeasonMapper;
import com.example.tire_warehouse.entity.Tire;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        SeasonMapper.class
})
public interface TireMapper {

    TireDto convertToDto(Tire tire);

    Tire convertToEntity(TireDto tireDto);
}
