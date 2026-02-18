package com.kaviya.securin.service;

import com.kaviya.securin.model.Recipe;
import com.kaviya.securin.repository.RecipeRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.io.InputStream;

@Service
public class DataIngestionService {
    private final RecipeRepository repository;

    public DataIngestionService(RecipeRepository repository) {
        this.repository = repository;
    }

    public void parseAndSave(InputStream inputStream) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inputStream);

        for (JsonNode node : root) {
            Recipe recipe = new Recipe();
            // Matching the exact keys from your US_recipes_null.json
            recipe.setTitle(node.path("title").asText());
            recipe.setCuisine(node.path("cuisine").asText());
            recipe.setDescription(node.path("description").asText());
            recipe.setServes(node.path("serves").asText());
            
            // Handling NaN and null values as required
            recipe.setRating(checkNaN(node.path("rating")));
            recipe.setPrepTime(checkNaNInt(node.path("prep_time")));
            recipe.setCookTime(checkNaNInt(node.path("cook_time")));
            recipe.setTotalTime(checkNaNInt(node.path("total_time")));
            
            // Store nutrients as a JSON string for the H2 database
            recipe.setNutrients(node.path("nutrients").toString());
            
            repository.save(recipe);
        }
    }

    private Double checkNaN(JsonNode node) {
        // If the value is null, missing, or the string "NaN", return null
        if (node.isMissingNode() || node.isNull() || node.asText().equalsIgnoreCase("NaN")) return null;
        return node.asDouble();
    }

    private Integer checkNaNInt(JsonNode node) {
        if (node.isMissingNode() || node.isNull() || node.asText().equalsIgnoreCase("NaN")) return null;
        return node.asInt();
    }
}