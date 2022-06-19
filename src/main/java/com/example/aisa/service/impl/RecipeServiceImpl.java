package com.example.aisa.service.impl;

import com.example.aisa.dao.RecipeRepository;
import com.example.aisa.dto.CoffeeDTO;
import com.example.aisa.mapper.CoffeeMapper;
import com.example.aisa.model.Coffee;
import com.example.aisa.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final CoffeeMapper coffeeMapper;

    @Override
    public void addRecipe(CoffeeDTO coffee) {
        recipeRepository.saveAndFlush(coffeeMapper.toEntity(coffee));
    }

    @Override
    public List<String> getRecipes() {
        return recipeRepository.findAll()
                .stream()
                .map(Coffee::getName)
                .toList();
    }
}
