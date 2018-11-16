package codegeeks.spring5recipe.converters;

import codegeeks.spring5recipe.commands.UnitOfMeasureCommand;
import codegeeks.spring5recipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    private static final String ID = "1";
    private static final String DESCRIPTION = "description";

    private UnitOfMeasureCommandToUnitOfMeasure converter;

    @Before
    public void setUp() {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convert() {
        // given
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);

        // when
        UnitOfMeasure domainObject = converter.convert(command);

        // then
        assertNotNull(domainObject);
        assertEquals(ID, domainObject.getId());
        assertEquals(DESCRIPTION, domainObject.getDescription());
    }

}