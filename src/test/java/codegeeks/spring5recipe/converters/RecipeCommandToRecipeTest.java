package codegeeks.spring5recipe.converters;

import codegeeks.spring5recipe.commands.CategoryCommand;
import codegeeks.spring5recipe.commands.IngredientCommand;
import codegeeks.spring5recipe.commands.NotesCommand;
import codegeeks.spring5recipe.commands.RecipeCommand;
import codegeeks.spring5recipe.domain.Difficulty;
import codegeeks.spring5recipe.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

    private static final Long ID = 1L;
    private static final String DESCRIPTION = "description";
    private static final Integer COOK_TIME = 2;
    private static final Integer PREP_TIME = 3;
    private static final String DIRECTIONS = "directions";
    private static final Difficulty DIFFICULTY = Difficulty.EASY;
    private static final Integer SERVINGS = 4;
    private static final String SOURCE = "source";
    private static final String URL = "url";
    private static final Long CATEGORY_ID_1 = 5L;
    private static final Long CATEGORY_ID_2 = 6L;
    private static final Long INGREDIENT_ID_1 = 7L;
    private static final Long INGREDIENT_ID_2 = 8L;
    private static final Long NOTES_ID = 9L;

    private RecipeCommandToRecipe converter;

    @Before
    public void setUp() {
        NotesCommandToNotes notesCommandToNotes = new NotesCommandToNotes();
        CategoryCommandToCategory categoryCommandToCategory = new CategoryCommandToCategory();
        IngredientCommandToIngredient ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
        converter = new RecipeCommandToRecipe(notesCommandToNotes, categoryCommandToCategory, ingredientCommandToIngredient);
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void convert() {
        // given
        RecipeCommand command = new RecipeCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);
        command.setPrepTime(PREP_TIME);
        command.setCookTime(COOK_TIME);
        command.setServings(SERVINGS);
        command.setSource(SOURCE);
        command.setUrl(URL);
        command.setDirections(DIRECTIONS);

        IngredientCommand ingredientCommand1 = new IngredientCommand();
        ingredientCommand1.setId(INGREDIENT_ID_1);

        IngredientCommand ingredientCommand2 = new IngredientCommand();
        ingredientCommand2.setId(INGREDIENT_ID_2);

        command.getIngredients().add(ingredientCommand1);
        command.getIngredients().add(ingredientCommand2);

        command.setDifficulty(DIFFICULTY);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);
        command.setNotes(notesCommand);

        CategoryCommand categoryCommand1 = new CategoryCommand();
        categoryCommand1.setId(CATEGORY_ID_1);

        CategoryCommand categoryCommand2 = new CategoryCommand();
        categoryCommand2.setId(CATEGORY_ID_2);

        command.getCategories().add(categoryCommand1);
        command.getCategories().add(categoryCommand2);


        // when
        Recipe domainObject = converter.convert(command);

        // then
        assertNotNull(domainObject);
        assertEquals(ID, domainObject.getId());
        assertEquals(DESCRIPTION, domainObject.getDescription());
        assertEquals(COOK_TIME, domainObject.getCookTime());
        assertEquals(PREP_TIME, domainObject.getPrepTime());
        assertEquals(DIFFICULTY, domainObject.getDifficulty());
        assertEquals(DIRECTIONS, domainObject.getDirections());
        assertEquals(SERVINGS, domainObject.getServings());
        assertEquals(SOURCE, domainObject.getSource());
        assertEquals(URL, domainObject.getUrl());
        assertEquals(NOTES_ID, domainObject.getNotes().getId());
        assertEquals(2, domainObject.getCategories().size());
        assertEquals(2, domainObject.getIngredients().size());
    }

}