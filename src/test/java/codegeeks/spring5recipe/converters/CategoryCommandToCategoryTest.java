package codegeeks.spring5recipe.converters;

import codegeeks.spring5recipe.commands.CategoryCommand;
import codegeeks.spring5recipe.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    private static final String ID = "1";
    private static final String DESCRIPTION = "description";

    private CategoryCommandToCategory converter;

    @Before
    public void setUp() {
        converter = new CategoryCommandToCategory();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() {
        // given
        CategoryCommand command = new CategoryCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);

        // when
        Category domainObject = converter.convert(command);

        // then
        assertNotNull(domainObject);
        assertEquals(ID, domainObject.getId());
        assertEquals(DESCRIPTION, domainObject.getDescription());
    }

}