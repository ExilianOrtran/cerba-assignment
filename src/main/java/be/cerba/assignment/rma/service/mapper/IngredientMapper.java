package be.cerba.assignment.rma.service.mapper;

import be.cerba.assignment.rma.openapi.model.BareIngredient;
import be.cerba.assignment.rma.openapi.model.BaseIngredient;
import be.cerba.assignment.rma.openapi.model.Ingredient;
import be.cerba.assignment.rma.persistence.entity.IngredientEntity;
import be.cerba.assignment.rma.persistence.repository.RecipeRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class IngredientMapper {

    @Autowired
    protected RecipeRepository recipeRepository;

    public abstract IngredientEntity updateIngredientEntityFromBareIngredient(BaseIngredient baseIngredient, @MappingTarget IngredientEntity ingredientEntity);

    @Mapping(target = "recipe", expression = "java(recipeRepository.findRecipeEntityById(bareIngredient.getRecipeId()))")
    public abstract IngredientEntity mapBareIngredient(BareIngredient bareIngredient);

    @Mapping(target = "recipe", expression = "java(recipeRepository.findRecipeEntityById(ingredient.getRecipeId()))")
    public abstract IngredientEntity mapIngredient(Ingredient ingredient);

    @Mapping(target = "recipeId", expression = "java(ingredientEntity.getRecipe().getId())")
    public abstract Ingredient mapIngredientEntity(IngredientEntity ingredientEntity);

    public abstract List<Ingredient> mapIIngredientEntities(List<IngredientEntity> ingredientEntities);
}
