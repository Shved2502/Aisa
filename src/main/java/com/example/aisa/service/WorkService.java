package com.example.aisa.service;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Validated
public interface WorkService {

    String getStatus();
    void makeCoffee(@NotNull @Pattern(regexp = "[a-zA-Z]+") String coffeeName);
    void updateContainer(@NotNull @Pattern(regexp = "[a-zA-Z]+") String container);
}
