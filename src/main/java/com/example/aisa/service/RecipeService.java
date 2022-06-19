package com.example.aisa.service;

import com.example.aisa.dto.CoffeeDTO;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface RecipeService {

    void addRecipe(@NotNull CoffeeDTO coffee);
    List<String> getRecipes();
}
