package com.example.aisa.unit;

import com.example.aisa.dao.RecipeRepository;
import com.example.aisa.dto.CoffeeDTO;
import com.example.aisa.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
class RecipeServiceTest {

    @MockBean
    private RecipeRepository recipeRepository;
    @Autowired
    private RecipeService recipeService;

    @Test
    void addRecipe_nullRecipe_ExceptionThrows() {
        assertThrows(ConstraintViolationException.class,
                () -> recipeService.addRecipe(null));
    }

    @Test
    void addRecipe_notNullRecipe_NothingWasThrown() {
        CoffeeDTO coffeeDTO = CoffeeDTO.builder()
                .id(1L)
                .name("Coffee")
                .coffeeConsumption(10)
                .milkConsumption(10)
                .waterConsumption(10)
                .purityConsumption(10)
                .timeConsumption(10).build();

        recipeService.addRecipe(coffeeDTO);

        verify(recipeRepository).saveAndFlush(any());
    }
}
