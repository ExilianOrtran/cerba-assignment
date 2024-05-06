package be.cerba.assignment.rma.service.mapper;

import be.cerba.assignment.rma.openapi.model.BareRecipe;
import be.cerba.assignment.rma.openapi.model.FullRecipe;
import be.cerba.assignment.rma.openapi.model.Recipe;
import be.cerba.assignment.rma.persistence.entity.RecipeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = IngredientMapper.class)
public interface RecipeMapper {

    RecipeEntity updateRecipeEntityFromBareRecipe(BareRecipe bareRecipe, @MappingTarget RecipeEntity recipeEntity);

    RecipeEntity mapBareRecipe(BareRecipe bareRecipe);

    Recipe mapRecipeEntity(RecipeEntity recipeEntity);
    List<Recipe> mapRecipeEntities(List<RecipeEntity> recipeEntities);

    @Mapping(source = "ingredientEntities", target = "ingredients")
    FullRecipe mapRecipeEntityWithIngredients(RecipeEntity recipeEntity);
}
