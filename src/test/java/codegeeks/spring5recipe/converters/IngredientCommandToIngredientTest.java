package codegeeks.spring5recipe.converters;

import codegeeks.spring5recipe.commands.IngredientCommand;
import codegeeks.spring5recipe.commands.UnitOfMeasureCommand;
import codegeeks.spring5recipe.domain.Ingredient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    private static final Long ID = 1L;
    private static final String DESCRIPTION = "description";
    private static final BigDecimal AMOUNT = new BigDecimal(1);
    private static final Long UOM_ID = 2L;
    private static final String UOM_DESCRIPTION = "Teaspoon";

    private IngredientCommandToIngredient converter;

    @Before
    public void setUp() {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void convert() {
        // given
        IngredientCommand command = new IngredientCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);
        command.setAmount(AMOUNT);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UOM_ID);
        unitOfMeasureCommand.setDescription(UOM_DESCRIPTION);
        command.setUnitOfMeasure(unitOfMeasureCommand);

        // when
        Ingredient domainObject = converter.convert(command);

        // then
        assertNotNull(domainObject);
        assertEquals(ID, domainObject.getId());
        assertEquals(DESCRIPTION, domainObject.getDescription());
        assertEquals(AMOUNT, domainObject.getAmount());
        assertNotNull(domainObject.getUnitOfMeasure());
        assertEquals(UOM_ID, domainObject.getUnitOfMeasure().getId());
        assertEquals(UOM_DESCRIPTION, domainObject.getUnitOfMeasure().getDescription());
    }

    @Test
    public void convertWithNullUOM() {
        // given
        IngredientCommand command = new IngredientCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);
        command.setAmount(AMOUNT);

        // when
        Ingredient domainObject = converter.convert(command);

        // then
        assertNotNull(domainObject);
        assertEquals(ID, domainObject.getId());
        assertEquals(DESCRIPTION, domainObject.getDescription());
        assertEquals(AMOUNT, domainObject.getAmount());
        assertNull(domainObject.getUnitOfMeasure());
    }
}