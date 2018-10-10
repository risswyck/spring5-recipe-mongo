package codegeeks.spring5recipe.converters;

import codegeeks.spring5recipe.commands.IngredientCommand;
import codegeeks.spring5recipe.domain.Ingredient;
import codegeeks.spring5recipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    private static final Long ID = 1L;
    private static final String DESCRIPTION = "description";
    private static final BigDecimal AMOUNT = new BigDecimal(1);
    private static final Long UOM_ID = 2L;
    private static final String UOM_DESCRIPTION = "Teaspoon";

    private IngredientToIngredientCommand converter;

    @Before
    public void setUp() {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void convert() {
        // given
        Ingredient domainObject = new Ingredient();
        domainObject.setId(ID);
        domainObject.setDescription(DESCRIPTION);
        domainObject.setAmount(AMOUNT);
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(UOM_ID);
        unitOfMeasure.setDescription(UOM_DESCRIPTION);
        domainObject.setUnitOfMeasure(unitOfMeasure);

        // when
        IngredientCommand command = converter.convert(domainObject);

        // then
        assertNotNull(command);
        assertEquals(ID, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(AMOUNT, command.getAmount());
        assertNotNull(command.getUnitOfMeasure());
        assertEquals(UOM_ID, command.getUnitOfMeasure().getId());
        assertEquals(UOM_DESCRIPTION, command.getUnitOfMeasure().getDescription());
    }

    @Test
    public void convertWithNullUOM() {
        // given
        Ingredient domainObject = new Ingredient();
        domainObject.setId(ID);
        domainObject.setDescription(DESCRIPTION);
        domainObject.setAmount(AMOUNT);

        // when
        IngredientCommand command = converter.convert(domainObject);

        // then
        assertNotNull(command);
        assertEquals(ID, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(AMOUNT, command.getAmount());
        assertNull(command.getUnitOfMeasure());
    }
}