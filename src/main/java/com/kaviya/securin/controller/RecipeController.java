package com.kaviya.securin.controller;

import com.kaviya.securin.model.Recipe;
import com.kaviya.securin.repository.RecipeRepository;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes")
@CrossOrigin("*")
public class RecipeController {
    private final RecipeRepository repository;

    public RecipeController(RecipeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Page<Recipe> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("rating").descending());
        return repository.findAll(pageable);
    }

    @GetMapping("/search")
    public Page<Recipe> search(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String cuisine,
            @RequestParam(required = false) Double rating,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        return repository.searchRecipes(title, cuisine, rating, pageable);
    }
}