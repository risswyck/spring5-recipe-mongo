package codegeeks.spring5recipe.services;

import codegeeks.spring5recipe.commands.IngredientCommand;
import reactor.core.publisher.Mono;

public interface IngredientService {
    Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId);
    Mono<IngredientCommand> saveIngredientCommand(String recipeId, IngredientCommand ingredientCommand);
    Mono<Void> deleteByRecipeIdAndIngredientId(String recipeId, String ingredientId);
}
