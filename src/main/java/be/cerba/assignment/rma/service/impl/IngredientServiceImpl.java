package be.cerba.assignment.rma.service.impl;

import be.cerba.assignment.rma.openapi.model.BareIngredient;
import be.cerba.assignment.rma.openapi.model.BaseIngredient;
import be.cerba.assignment.rma.openapi.model.Ingredient;
import be.cerba.assignment.rma.persistence.entity.IngredientEntity;
import be.cerba.assignment.rma.persistence.repository.IngredientRepository;
import be.cerba.assignment.rma.service.IngredientService;
import be.cerba.assignment.rma.service.mapper.IngredientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    IngredientMapper ingredientMapper;

    @Override
    public Ingredient addIngredient(BareIngredient bareIngredient) {
        IngredientEntity savedIngredient = ingredientRepository.save(ingredientMapper.mapBareIngredient(bareIngredient));
        return ingredientMapper.mapIngredientEntity(savedIngredient);
    }

    @Override
    public Optional<Ingredient> updateIngredient(UUID uuid, BaseIngredient baseIngredient) {
        if (ingredientRepository.findById(uuid).isPresent()) {
            IngredientEntity updatedEntity = ingredientMapper.updateIngredientEntityFromBareIngredient(
                    baseIngredient,
                    ingredientRepository.findById(uuid).get());

            return Optional.of(ingredientMapper.mapIngredientEntity(ingredientRepository.save(updatedEntity)));
        }
        return Optional.empty();
    }

    @Override
    public void removeIngredient(UUID uuid) {
        ingredientRepository.deleteById(uuid);
    }

    @Override
    public Optional<Ingredient> getIngredient(UUID uuid) {
        Optional<IngredientEntity> ingredientEntity = ingredientRepository.findById(uuid);
        return ingredientEntity.map(ingredientMapper::mapIngredientEntity);
    }
}
