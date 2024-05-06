package be.cerba.assignment.rma;

import be.cerba.assignment.rma.persistence.entity.RecipeEntity;
import be.cerba.assignment.rma.persistence.repository.RecipeRepository;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Optional;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = {RecipeRepositoryTest.Initializer.class})
public class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:16.2")
            .withDatabaseName("integration-tests")
            .withUsername("sa")
            .withPassword("sa");

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + String.format("jdbc:tc:postgresql://localhost:%s/%s", postgreSQLContainer.getFirstMappedPort(), postgreSQLContainer.getDatabaseName()),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Test
    public void saveRecipe() {
        UUID uuid = recipeRepository.save(
                new RecipeEntity()
                        .setName("test")
                        .setDescription("testDes")
                        .setCookingInstruction("testCook")
        ).getId();

        Optional<RecipeEntity> recipe = recipeRepository.findById(uuid);
        Assert.assertTrue(recipe.isPresent());
        Assert.assertEquals(uuid, recipe.get().getId());
        Assert.assertEquals("test", recipe.get().getName());
        Assert.assertEquals("testDes", recipe.get().getDescription());
        Assert.assertEquals("testCook", recipe.get().getCookingInstruction());
        Assert.assertNotNull(recipe.get().getCreatedDate());
        Assert.assertNotNull(recipe.get().getLastModifiedDate());
    }

    @Test
    public void updateRecipe() {
        UUID uuid = recipeRepository.save(
                new RecipeEntity()
                        .setName("test")
                        .setDescription("testDes")
                        .setCookingInstruction("testCook")
        ).getId();

        RecipeEntity recipe = recipeRepository.findById(uuid).get();
        recipe.setName("newRecipe").setDescription("newDes").setCookingInstruction("newCook");
        recipeRepository.save(recipe);

        Optional<RecipeEntity> updatedRecipe = recipeRepository.findById(uuid);

        Assert.assertTrue(updatedRecipe.isPresent());
        Assert.assertEquals(uuid, updatedRecipe.get().getId());
        Assert.assertEquals("newRecipe", updatedRecipe.get().getName());
        Assert.assertEquals("newDes", updatedRecipe.get().getDescription());
        Assert.assertEquals("newCook", updatedRecipe.get().getCookingInstruction());
        Assert.assertNotNull(updatedRecipe.get().getCreatedDate());
        Assert.assertNotNull(updatedRecipe.get().getLastModifiedDate());
    }

    @AfterEach
    public void cleanDB() {
        recipeRepository.deleteAll();
    }
}