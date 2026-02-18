package com.kaviya.securin.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import java.io.InputStream;

@Component
public class DataInitializer implements CommandLineRunner {

    private final DataIngestionService dataIngestionService;

    public DataInitializer(DataIngestionService dataIngestionService) {
        this.dataIngestionService = dataIngestionService;
    }

    @Override
    public void run(String... args) throws Exception {
        // This looks for a file named recipes.json in src/main/resources
        ClassPathResource resource = new ClassPathResource("recipes.json");
        
        if (resource.exists()) {
            try (InputStream inputStream = resource.getInputStream()) {
                dataIngestionService.parseAndSave(inputStream);
                System.out.println("Successfully loaded recipe data into the database.");
            }
        } else {
            System.err.println("Warning: recipes.json not found in resources folder.");
        }
    }
}