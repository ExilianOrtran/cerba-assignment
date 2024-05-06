package be.cerba.assignment.rma.controller;

import be.cerba.assignment.rma.openapi.api.RecipesApi;
import be.cerba.assignment.rma.openapi.model.BareRecipe;
import be.cerba.assignment.rma.openapi.model.FullRecipe;
import be.cerba.assignment.rma.openapi.model.Recipe;
import be.cerba.assignment.rma.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class RecipeController implements RecipesApi {

    @Autowired
    private RecipeService recipeService;

    @Override
    public ResponseEntity<Recipe> createRecipe(BareRecipe bareRecipe) {
        return ResponseEntity.ok(recipeService.createRecipe(bareRecipe));
    }

    @Override
    public ResponseEntity<Recipe> updateRecipe(UUID uuid, BareRecipe bareRecipe) {
        return ResponseEntity.of(recipeService.updateRecipe(uuid, bareRecipe));
    }

    @Override
    public ResponseEntity<Void> deleteRecipe(UUID uuid) {
        recipeService.removeRecipe(uuid);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<FullRecipe> getRecipe(UUID uuid) {
        return ResponseEntity.of(recipeService.getRecipe(uuid));
    }

    @Override
    public ResponseEntity<List<Recipe>> getRecipes() {
        return ResponseEntity.ok(recipeService.getRecipes());
    }
}
