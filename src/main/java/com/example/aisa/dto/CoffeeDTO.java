package com.example.aisa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CoffeeDTO {

    private Long id;
    private String name;
    private Integer coffeeConsumption;
    private Integer milkConsumption;
    private Integer waterConsumption;
    private Integer purityConsumption;
    private Integer timeConsumption;
}
