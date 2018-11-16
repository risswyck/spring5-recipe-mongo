package codegeeks.spring5recipe.services;

import codegeeks.spring5recipe.commands.IngredientCommand;
import codegeeks.spring5recipe.converters.IngredientCommandToIngredient;
import codegeeks.spring5recipe.converters.IngredientToIngredientCommand;
import codegeeks.spring5recipe.converters.UnitOfMeasureCommandToUnitOfMeasure;
import codegeeks.spring5recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import codegeeks.spring5recipe.domain.Ingredient;
import codegeeks.spring5recipe.domain.Recipe;
import codegeeks.spring5recipe.repositories.RecipeRepository;
import codegeeks.spring5recipe.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {
    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private UnitOfMeasureRepository unitOfMeasureRepository;

    private IngredientService ingredientService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(
                recipeRepository,
                unitOfMeasureRepository,
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()));
    }

    @Test
    public void findByRecipeIdAndIngredientId() {
        // given
        Recipe recipe = new Recipe();
        recipe.setId("1");

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId("1");

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId("2");

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);

        when(recipeRepository.findById(anyString())).thenReturn(Optional.of(recipe));

        // when
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId("1", "2");

        // then
        assertEquals("2", ingredientCommand.getId());
        verify(recipeRepository, times(1)).findById(anyString());

    }

    @Test
    public void saveIngredientCommand() {
        // given
        IngredientCommand command = new IngredientCommand();
        command.setId("3");

        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        Recipe savedRecipe = new Recipe();
        savedRecipe.setId("2");
        Ingredient ingredient = new Ingredient();
        ingredient.setId("3");
        savedRecipe.addIngredient(ingredient);

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        // when
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(savedRecipe.getId(), command);

        // then
        assertEquals("3", savedCommand.getId());
        verify(recipeRepository, times(1)).findById(anyString());
        verify(recipeRepository, times(1)).save(any(Recipe.class));

    }

    @Test
    public void deleteByRecipeIdIngredientId() {
        // given
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId("1");

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId("2");

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId("3");

        Recipe recipe = new Recipe();
        recipe.setId("1");
        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        Recipe savedRecipe = new Recipe();
        savedRecipe.setId("1");
        savedRecipe.addIngredient(ingredient1);
        savedRecipe.addIngredient(ingredient3);

        /// when
        when(recipeRepository.findById(anyString())).thenReturn(Optional.of(recipe));
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        ingredientService.deleteByRecipeIdAndIngredientId(recipe.getId(), ingredient2.getId());

    }

}