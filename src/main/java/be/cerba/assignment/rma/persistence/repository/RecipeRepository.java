package be.cerba.assignment.rma.persistence.repository;

import be.cerba.assignment.rma.persistence.entity.RecipeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecipeRepository extends PagingAndSortingRepository<RecipeEntity, UUID>, ListCrudRepository<RecipeEntity, UUID> {

    @Query(value = "SELECT r FROM RecipeEntity r JOIN FETCH IngredientEntity i on i.recipe = r WHERE r.id = :uuid")
    Optional<RecipeEntity> findRecipeAndIngredientsById(UUID uuid);

    List<RecipeEntity> findAllByOrderByNameAsc();

    RecipeEntity findRecipeEntityById(UUID uuid);
}