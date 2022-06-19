package com.example.aisa.controller;

import com.example.aisa.dto.CoffeeDTO;
import com.example.aisa.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping()
    public ResponseEntity<List<String>> getRecipes() {
        List<String> recipes = recipeService.getRecipes();
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> addRecipe(@RequestBody CoffeeDTO coffee) {
        recipeService.addRecipe(coffee);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

// http://localhost:8080/swagger-ui/index.html#/