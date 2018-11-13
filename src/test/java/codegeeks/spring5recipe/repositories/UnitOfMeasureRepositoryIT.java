package codegeeks.spring5recipe.repositories;

import codegeeks.spring5recipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() {
    }

    @Test
    public void findByDescription() {
        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (uom.isPresent()) {
            assertEquals("Teaspoon", uom.get().getDescription());
        } else {
            fail("UOM must not be null");
        }
    }

    @Test
    public void findByDescriptionCup() {
        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByDescription("Cup");
        if (uom.isPresent()) {
            assertEquals("Cup", uom.get().getDescription());
        } else {
            fail("UOM must not be null");
        }
    }
}