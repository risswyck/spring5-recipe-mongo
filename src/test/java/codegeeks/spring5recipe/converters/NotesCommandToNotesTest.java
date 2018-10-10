package codegeeks.spring5recipe.converters;

import codegeeks.spring5recipe.commands.NotesCommand;
import codegeeks.spring5recipe.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    private static final Long ID = 1L;
    private static final String RECIPE_NOTES = "description";

    private NotesCommandToNotes converter;

    @Before
    public void setUp() {
        converter = new NotesCommandToNotes();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convert() {
        // given
        NotesCommand command = new NotesCommand();
        command.setId(ID);
        command.setRecipeNotes(RECIPE_NOTES);

        // when
        Notes domainObject = converter.convert(command);

        // then
        assertNotNull(domainObject);
        assertEquals(ID, domainObject.getId());
        assertEquals(RECIPE_NOTES, domainObject.getRecipeNotes());
    }

}