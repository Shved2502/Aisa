package com.example.aisa.dao;

import com.example.aisa.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Coffee, Long> {

    Optional<Coffee> findByName(String name);
}
