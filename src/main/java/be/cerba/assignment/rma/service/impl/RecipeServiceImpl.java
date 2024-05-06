package be.cerba.assignment.rma.service.impl;

import be.cerba.assignment.rma.openapi.model.BareRecipe;
import be.cerba.assignment.rma.openapi.model.FullRecipe;
import be.cerba.assignment.rma.openapi.model.Recipe;
import be.cerba.assignment.rma.persistence.entity.RecipeEntity;
import be.cerba.assignment.rma.persistence.repository.RecipeRepository;
import be.cerba.assignment.rma.service.RecipeService;
import be.cerba.assignment.rma.service.mapper.RecipeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private RecipeMapper recipeMapper;

    @Override
    public Recipe createRecipe(BareRecipe recipe) {
        RecipeEntity savedRecipe = recipeRepository.save(recipeMapper.mapBareRecipe(recipe));
        return recipeMapper.mapRecipeEntity(savedRecipe);
    }

    @Override
    public Optional<Recipe> updateRecipe(UUID uuid, BareRecipe bareRecipe) {
        Optional<RecipeEntity> recipeEntityOptional = recipeRepository.findById(uuid);
        if (recipeEntityOptional.isPresent()) {
            RecipeEntity updatedRecipeEntity = recipeMapper.updateRecipeEntityFromBareRecipe(
                    bareRecipe,
                    recipeEntityOptional.get());
            return Optional.of(recipeMapper.mapRecipeEntity(recipeRepository.save(updatedRecipeEntity)));
        }
        return Optional.empty();
    }

    @Override
    public void removeRecipe(UUID uuid) {
        recipeRepository.deleteById(uuid);
    }

    @Override
    public Optional<FullRecipe> getRecipe(UUID uuid) {
        Optional<RecipeEntity> recipeEntityOptional = recipeRepository.findRecipeAndIngredientsById(uuid);
        return recipeEntityOptional.map(recipeMapper::mapRecipeEntityWithIngredients);
    }

    @Override
    public List<Recipe> getRecipes() {
        return recipeMapper.mapRecipeEntities(recipeRepository.findAllByOrderByNameAsc());
    }
}
