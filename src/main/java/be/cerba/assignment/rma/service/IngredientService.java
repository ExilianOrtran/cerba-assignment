package be.cerba.assignment.rma.service;

import be.cerba.assignment.rma.openapi.model.BareIngredient;
import be.cerba.assignment.rma.openapi.model.BaseIngredient;
import be.cerba.assignment.rma.openapi.model.Ingredient;

import java.util.Optional;
import java.util.UUID;

public interface IngredientService {

    Ingredient addIngredient(BareIngredient bareIngredient);
    Optional<Ingredient> updateIngredient(UUID uuid, BaseIngredient baseIngredient);
    void removeIngredient(UUID uuid);
    Optional<Ingredient> getIngredient(UUID uuid);
}
