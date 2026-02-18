package com.kaviya.securin.repository;

import com.kaviya.securin.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query("SELECT r FROM Recipe r WHERE " +
           "(:title IS NULL OR LOWER(r.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
           "(:cuisine IS NULL OR LOWER(r.cuisine) LIKE LOWER(CONCAT('%', :cuisine, '%'))) AND " +
           "(:rating IS NULL OR r.rating >= :rating)")
    Page<Recipe> searchRecipes(
            @Param("title") String title, 
            @Param("cuisine") String cuisine, 
            @Param("rating") Double rating, 
            Pageable pageable);
}