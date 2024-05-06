package be.cerba.assignment.rma.persistence.repository;

import be.cerba.assignment.rma.persistence.entity.IngredientEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IngredientRepository extends CrudRepository<IngredientEntity, UUID> {
}
