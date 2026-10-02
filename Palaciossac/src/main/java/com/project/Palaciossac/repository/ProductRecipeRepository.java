package com.project.Palaciossac.repository;

import com.project.Palaciossac.entity.ProductRecipe;
import com.project.Palaciossac.entity.ProductRecipeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRecipeRepository extends JpaRepository<ProductRecipe, ProductRecipeId> {

    List<ProductRecipe> findByProductId(Long productId);
}
