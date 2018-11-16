package codegeeks.spring5recipe.converters;

import codegeeks.spring5recipe.commands.RecipeCommand;
import codegeeks.spring5recipe.domain.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

    private static final String ID = "1";
    private static final String DESCRIPTION = "description";
    private static final Integer COOK_TIME = 2;
    private static final Integer PREP_TIME = 3;
    private static final String DIRECTIONS = "directions";
    private static final Difficulty DIFFICULTY = Difficulty.EASY;
    private static final Integer SERVINGS = 4;
    private static final String SOURCE = "source";
    private static final String URL = "url";
    private static final String CATEGORY_ID_1 = "5";
    private static final String CATEGORY_ID_2 = "6";
    private static final String INGREDIENT_ID_1 = "7";
    private static final String INGREDIENT_ID_2 = "8";
    private static final String NOTES_ID = "9";

    private RecipeToRecipeCommand converter;

    @Before
    public void setUp() {
        NotesToNotesCommand notesToNotesCommand = new NotesToNotesCommand();
        CategoryToCategoryCommand categoryToCategoryCommand = new CategoryToCategoryCommand();
        IngredientToIngredientCommand ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        converter = new RecipeToRecipeCommand(notesToNotesCommand, categoryToCategoryCommand, ingredientToIngredientCommand);
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void convert() {
        // given
        Recipe domainObject = new Recipe();
        domainObject.setId(ID);
        domainObject.setDescription(DESCRIPTION);
        domainObject.setPrepTime(PREP_TIME);
        domainObject.setCookTime(COOK_TIME);
        domainObject.setServings(SERVINGS);
        domainObject.setSource(SOURCE);
        domainObject.setUrl(URL);
        domainObject.setDirections(DIRECTIONS);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(INGREDIENT_ID_1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGREDIENT_ID_2);

        domainObject.getIngredients().add(ingredient1);
        domainObject.getIngredients().add(ingredient2);

        domainObject.setDifficulty(DIFFICULTY);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        domainObject.setNotes(notes);

        Category category1 = new Category();
        category1.setId(CATEGORY_ID_1);

        Category category2 = new Category();
        category2.setId(CATEGORY_ID_2);

        domainObject.getCategories().add(category1);
        domainObject.getCategories().add(category2);


        // when
        RecipeCommand command = converter.convert(domainObject);

        // then
        assertNotNull(command);
        assertEquals(ID, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(COOK_TIME, command.getCookTime());
        assertEquals(PREP_TIME, command.getPrepTime());
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertEquals(DIRECTIONS, command.getDirections());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertEquals(NOTES_ID, command.getNotes().getId());
        assertEquals(2, command.getCategories().size());
        assertEquals(2, command.getIngredients().size());
    }

}