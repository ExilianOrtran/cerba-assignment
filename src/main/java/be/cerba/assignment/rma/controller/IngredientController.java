package be.cerba.assignment.rma.controller;

import be.cerba.assignment.rma.openapi.api.IngredientsApi;
import be.cerba.assignment.rma.openapi.model.BareIngredient;
import be.cerba.assignment.rma.openapi.model.BaseIngredient;
import be.cerba.assignment.rma.openapi.model.Ingredient;
import be.cerba.assignment.rma.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class IngredientController implements IngredientsApi {

    @Autowired
    private IngredientService ingredientService;

    @Override
    public ResponseEntity<Ingredient> addIngredient(BareIngredient bareIngredient) {
        return ResponseEntity.ok(ingredientService.addIngredient(bareIngredient));
    }

    @Override
    public ResponseEntity<Ingredient> updateIngredient(UUID uuid, BaseIngredient baseIngredient) {
        return ResponseEntity.of(ingredientService.updateIngredient(uuid, baseIngredient));
    }

    @Override
    public ResponseEntity<Void> deleteIngredient(UUID uuid) {
        ingredientService.removeIngredient(uuid);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Ingredient> getIngredient(UUID uuid) {
        return ResponseEntity.of(ingredientService.getIngredient(uuid));
    }
}
