package codegeeks.spring5recipe.converters;

import codegeeks.spring5recipe.commands.CategoryCommand;
import codegeeks.spring5recipe.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    private static final Long ID = 1L;
    private static final String DESCRIPTION = "description";

    private CategoryToCategoryCommand converter;

    @Before
    public void setUp() {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void convert() {
        // given
        Category domainObject = new Category();
        domainObject.setId(ID);
        domainObject.setDescription(DESCRIPTION);

        // when
        CategoryCommand command = converter.convert(domainObject);

        // then
        assertNotNull(command);
        assertEquals(ID, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());

    }
}