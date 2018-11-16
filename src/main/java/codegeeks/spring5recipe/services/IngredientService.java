package codegeeks.spring5recipe.services;

import codegeeks.spring5recipe.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String ingredientId);
    IngredientCommand saveIngredientCommand(String recipeId, IngredientCommand ingredientCommand);
    void deleteByRecipeIdAndIngredientId(String recipeId, String ingredientId);
}
