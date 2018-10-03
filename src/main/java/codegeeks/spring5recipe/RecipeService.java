package codegeeks.spring5recipe;

import codegeeks.spring5recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}
