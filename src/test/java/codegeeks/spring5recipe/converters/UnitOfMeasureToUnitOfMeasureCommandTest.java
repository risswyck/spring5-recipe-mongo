package codegeeks.spring5recipe.converters;

import codegeeks.spring5recipe.commands.UnitOfMeasureCommand;
import codegeeks.spring5recipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    private static final String ID = "1";
    private static final String DESCRIPTION = "description";

    private UnitOfMeasureToUnitOfMeasureCommand converter;

    @Before
    public void setUp() {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    public void convert() {
        // given
        UnitOfMeasure domainObject = new UnitOfMeasure();
        domainObject.setId(ID);
        domainObject.setDescription(DESCRIPTION);

        // when
        UnitOfMeasureCommand command = converter.convert(domainObject);

        // then
        assertNotNull(command);
        assertEquals(ID, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());

    }
}