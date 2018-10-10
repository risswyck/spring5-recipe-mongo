package codegeeks.spring5recipe.converters;

import codegeeks.spring5recipe.commands.NotesCommand;
import codegeeks.spring5recipe.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {

    private static final Long ID = 1L;
    private static final String RECIPE_NOTES = "description";

    private NotesToNotesCommand converter;

    @Before
    public void setUp() {
        converter = new NotesToNotesCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void convert() {
        // given
        Notes domainObject = new Notes();
        domainObject.setId(ID);
        domainObject.setRecipeNotes(RECIPE_NOTES);

        // when
        NotesCommand command = converter.convert(domainObject);

        // then
        assertNotNull(command);
        assertEquals(ID, command.getId());
        assertEquals(RECIPE_NOTES, command.getRecipeNotes());

    }
}