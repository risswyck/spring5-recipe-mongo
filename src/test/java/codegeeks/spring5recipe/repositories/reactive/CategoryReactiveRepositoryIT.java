package codegeeks.spring5recipe.repositories.reactive;

import codegeeks.spring5recipe.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryIT {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp() {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void findByDescription() {
        Category category = new Category();
        category.setDescription("Mexican");
        Category savedCategory = categoryReactiveRepository.save(category).block();
        assertNotNull(savedCategory);
        assertEquals(category.getDescription(), savedCategory.getDescription());

        Category foundCategory = categoryReactiveRepository.findByDescription(category.getDescription()).block();
        assertNotNull(foundCategory);
        assertEquals(category.getDescription(), foundCategory.getDescription());
        assertEquals((Long)1L, categoryReactiveRepository.count().block());
    }

}