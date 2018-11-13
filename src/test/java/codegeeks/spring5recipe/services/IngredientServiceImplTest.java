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
import static org.mockito.ArgumentMatchers.anyLong;
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
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        // when
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 2L);

        // then
        assertEquals(Long.valueOf(2L), ingredientCommand.getId());
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());

    }

    @Test
    public void saveIngredientCommand() {
        // given
        IngredientCommand command = new IngredientCommand();
        command.setId(3L);
        command.setRecipeId(2L);

        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        Recipe savedRecipe = new Recipe();
        savedRecipe.setId(2L);
        Ingredient ingredient = new Ingredient();
        ingredient.setId(3L);
        savedRecipe.addIngredient(ingredient);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        // when
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        // then
        assertEquals(Long.valueOf(3L), savedCommand.getId());
        assertEquals(Long.valueOf(2L), savedCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));

    }

    @Test
    public void deleteByRecipeIdIngredientId() {
        // given
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        Recipe savedRecipe = new Recipe();
        savedRecipe.setId(1L);
        savedRecipe.addIngredient(ingredient1);
        savedRecipe.addIngredient(ingredient3);

        /// when
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        ingredientService.deleteByRecipeIdAndIngredientId(recipe.getId(), ingredient2.getId());

    }

}