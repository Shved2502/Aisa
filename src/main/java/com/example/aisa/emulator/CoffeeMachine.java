package com.example.aisa.emulator;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Data
@Component
public class CoffeeMachine {

    private final Integer maxCoffee = 100;
    private final Integer maxMilk = 1000;
    private final Integer maxWater = 1500;
    private final Integer maxPurity = 100;
    private final Integer minPurity = 20;
    private final Integer timeToClean = 20;

    private String status;
    private Integer coffee;
    private Integer milk;
    private Integer water;
    private Integer purity;

    public CoffeeMachine() {
        coffee = maxCoffee;
        milk = maxMilk;
        water = maxWater;
        purity = maxPurity;
    }

    public void processing(Integer seconds) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
