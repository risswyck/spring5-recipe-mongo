package codegeeks.spring5recipe.repositories.reactive;

import codegeeks.spring5recipe.bootstrap.RecipeBootstrap;
import codegeeks.spring5recipe.domain.Category;
import codegeeks.spring5recipe.domain.Recipe;
import codegeeks.spring5recipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RecipeReactiveRepositoryIT {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Autowired
    RecipeReactiveRepository recipeReactiveRepository;

    @Before
    public void setUp() {
        recipeReactiveRepository.deleteAll().block();
        unitOfMeasureReactiveRepository.deleteAll().block();
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void findByDescription() {
        Long categoryCount = categoryReactiveRepository.saveAll(RecipeBootstrap.buildCategoryList()).count().block();
        assertEquals((Long)4L, categoryCount);

        Recipe recipe = new Recipe();
        recipe.setDescription("recipe");
        recipe.getCategories().add(categoryReactiveRepository.findByDescription("Mexican").block());
        recipe.getCategories().add(categoryReactiveRepository.findByDescription("Fast Food").block());

        Recipe savedRecipe = recipeReactiveRepository.save(recipe).block();
        assertNotNull(savedRecipe.getId());

        assertEquals((Long)1L, recipeReactiveRepository.count().block());

        Recipe foundRecipe = recipeReactiveRepository.findAll().blockLast();
        assertEquals(recipe.getDescription(), foundRecipe.getDescription());
        assertTrue(recipe.getCategories().equals(foundRecipe.getCategories()));
    }

}