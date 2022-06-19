package com.example.aisa.mapper;

import com.example.aisa.dto.CoffeeDTO;
import com.example.aisa.model.Coffee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoffeeMapper extends EntityMapper<CoffeeDTO, Coffee> {
}
