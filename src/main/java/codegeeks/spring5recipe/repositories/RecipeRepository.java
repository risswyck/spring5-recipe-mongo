package codegeeks.spring5recipe.repositories;

import codegeeks.spring5recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, String> {
}
