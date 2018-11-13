package codegeeks.spring5recipe.controllers;

import codegeeks.spring5recipe.commands.RecipeCommand;
import codegeeks.spring5recipe.domain.Recipe;
import codegeeks.spring5recipe.exceptions.NotFoundException;
import codegeeks.spring5recipe.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        RecipeController controller = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void testGetRecipe() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        when(recipeService.findById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void testRecipeNotFound() throws Exception {
        when(recipeService.findById(anyLong())).thenThrow(new NotFoundException("Recipe not found. For Recipe ID 42"));

        mockMvc.perform(get("/recipe/42/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("recipe/404Error"))
                .andExpect(model().attributeExists("exception"));
    }

    @Test
    public void testRecipeBadRequest() throws Exception {
        mockMvc.perform(get("/recipe/not_a_number/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("recipe/400Error"))
                .andExpect(model().attributeExists("exception"));
    }

    @Test
    public void testGetNewRecipeForm() throws Exception {
        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void testPostNewRecipeForm() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(42L);

        when(recipeService.saveRecipeCommand(any())).thenReturn(command);

        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "Awesome recipe")
                .param("directions", "Some directions")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/42/show"));
    }

    @Test
    public void testPostNewRecipeFormWithValidationError() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(42L);

        when(recipeService.saveRecipeCommand(any())).thenReturn(command);

        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "Awesome recipe")
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/recipeform"));
    }

    @Test
    public void testGetUpdateView() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(42L);

        when(recipeService.findCommandById(any())).thenReturn(command);

        mockMvc.perform(get("/recipe/42/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void testDeleteAction() throws Exception {
        mockMvc.perform(get("/recipe/42/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(recipeService, times(1)).deleteById(anyLong());
    }
}
