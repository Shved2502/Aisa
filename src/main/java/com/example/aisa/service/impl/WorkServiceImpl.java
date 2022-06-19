package com.example.aisa.service.impl;

import com.example.aisa.dao.RecipeRepository;
import com.example.aisa.dao.WorkRepository;
import com.example.aisa.emulator.CoffeeMachine;
import com.example.aisa.enums.Status;
import com.example.aisa.model.Coffee;
import com.example.aisa.model.Log;
import com.example.aisa.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WorkServiceImpl implements WorkService {

    private final WorkRepository workRepository;
    private final CoffeeMachine coffeeMachine;
    private final RecipeRepository recipeRepository;

    @Override
    public String getStatus() {
        return coffeeMachine.getStatus();
    }

    @Override
    public void makeCoffee(String coffeeName) {
        Coffee coffee = recipeRepository.findByName(coffeeName).get();
        if (checkIngredients(coffee)) {
            workRepository.saveAndFlush(Log.builder()
                    .dateTime(LocalDateTime.now())
                    .status(String.format("Start making %s", coffeeName.toLowerCase())).build());

            callMakeCoffee(coffee);

            workRepository.saveAndFlush(Log.builder()
                    .dateTime(LocalDateTime.now())
                    .status(String.format("%s is ready!", coffeeName)).build());

            clean();
        } else {
            workRepository.saveAndFlush(Log.builder()
                    .dateTime(LocalDateTime.now())
                    .status("Not enough ingredients! Please fill the containers").build());
        }
    }

    @Override
    public void updateContainer(String container) {
        switch (container) {
            case "Coffee" -> coffeeMachine.setCoffee(coffeeMachine.getMaxCoffee());
            case "Milk" -> coffeeMachine.setMilk(coffeeMachine.getMaxMilk());
            case "Water" -> coffeeMachine.setWater(coffeeMachine.getMaxWater());
        }

        coffeeMachine.setStatus(Status.READY.name());

        workRepository.saveAndFlush(Log.builder()
                .dateTime(LocalDateTime.now())
                .status(String.format("%s container filled", container)).build());
    }

    private void callMakeCoffee(Coffee coffee) {
        coffeeMachine.setStatus(Status.MAKING + " " + coffee.getName().toUpperCase());
        coffeeMachine.setCoffee(coffeeMachine.getCoffee() - coffee.getCoffeeConsumption());
        coffeeMachine.setMilk(coffeeMachine.getMilk() - coffee.getMilkConsumption());
        coffeeMachine.setWater(coffeeMachine.getWater() - coffee.getWaterConsumption());
        coffeeMachine.setPurity(coffeeMachine.getPurity() - coffee.getPurityConsumption());

        coffeeMachine.processing(coffee.getTimeConsumption());

        coffeeMachine.setStatus(coffee.getName().toUpperCase() + " IS " + Status.READY.name());
    }

    private boolean checkIngredients(Coffee coffee) {
        if (coffeeMachine.getCoffee() < coffee.getCoffeeConsumption()) {
            coffeeMachine.setStatus(Status.WAITING.name() + " to fill coffee container".toUpperCase());
            return false;
        } else if (coffeeMachine.getMilk() < coffee.getMilkConsumption()) {
            coffeeMachine.setStatus(Status.WAITING.name() + " to fill milk container".toUpperCase());
            return false;
        } else if (coffeeMachine.getWater() < coffee.getWaterConsumption()) {
            coffeeMachine.setStatus(Status.WAITING.name() + " to fill water container".toUpperCase());
            return false;
        } else {
            return true;
        }
    }

    private void clean() {
        if (coffeeMachine.getPurity() < coffeeMachine.getMinPurity()) {
            workRepository.saveAndFlush(Log.builder()
                    .dateTime(LocalDateTime.now())
                    .status("Start cleaning coffee machine").build());

            coffeeMachine.setStatus(Status.CLEANING.name());
            coffeeMachine.processing(coffeeMachine.getTimeToClean());
            coffeeMachine.setPurity(coffeeMachine.getMaxPurity());
            coffeeMachine.setStatus(Status.READY.name());

            workRepository.saveAndFlush(Log.builder()
                    .dateTime(LocalDateTime.now())
                    .status("Cleaning completed").build());
        }
    }
}
