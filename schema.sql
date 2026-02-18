-- Database Schema for Securin Recipe Assessment
-- This script defines the structure for the recipes table

CREATE TABLE recipes (
    id SERIAL PRIMARY KEY,
    cuisine VARCHAR(255),
    title VARCHAR(255),
    rating DOUBLE PRECISION,
    prep_time INTEGER,
    cook_time INTEGER,
    total_time INTEGER,
    description TEXT,
    nutrients TEXT, -- Stores JSON data as a string
    serves VARCHAR(255)
);

-- Index for faster search performance on title and cuisine
CREATE INDEX idx_recipe_title ON recipes(title);
CREATE INDEX idx_recipe_cuisine ON recipes(cuisine);