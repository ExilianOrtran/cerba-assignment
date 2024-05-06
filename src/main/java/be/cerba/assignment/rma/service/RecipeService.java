package be.cerba.assignment.rma.service;

import be.cerba.assignment.rma.openapi.model.BareRecipe;
import be.cerba.assignment.rma.openapi.model.FullRecipe;
import be.cerba.assignment.rma.openapi.model.Recipe;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecipeService {

    Recipe createRecipe(BareRecipe recipe);
    Optional<Recipe> updateRecipe(UUID uuid, BareRecipe bareRecipe);
    void removeRecipe(UUID uuid);
    Optional<FullRecipe> getRecipe(UUID uuid);
    List<Recipe> getRecipes();
}