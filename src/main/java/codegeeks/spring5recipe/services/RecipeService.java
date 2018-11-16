package codegeeks.spring5recipe.services;

import codegeeks.spring5recipe.commands.RecipeCommand;
import codegeeks.spring5recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(String id);
    RecipeCommand findCommandById(String id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    void deleteById(String id);
}
