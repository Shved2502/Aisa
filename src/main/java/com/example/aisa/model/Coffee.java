package com.example.aisa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "coffee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer coffeeConsumption;
    private Integer milkConsumption;
    private Integer waterConsumption;
    private Integer purityConsumption;
    private Integer timeConsumption;
}
