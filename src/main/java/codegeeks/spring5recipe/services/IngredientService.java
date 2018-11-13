package codegeeks.spring5recipe.services;

import codegeeks.spring5recipe.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
    void deleteByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
