package codegeeks.spring5recipe.services;

import codegeeks.spring5recipe.commands.RecipeCommand;
import codegeeks.spring5recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long id);
    RecipeCommand findCommandById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    void deleteById(Long id);
}
