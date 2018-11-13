package codegeeks.spring5recipe.services;

import codegeeks.spring5recipe.commands.RecipeCommand;
import codegeeks.spring5recipe.converters.RecipeCommandToRecipe;
import codegeeks.spring5recipe.converters.RecipeToRecipeCommand;
import codegeeks.spring5recipe.domain.Recipe;
import codegeeks.spring5recipe.exceptions.NotFoundException;
import codegeeks.spring5recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toSet;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("I'm in the service");
        return StreamSupport.stream(recipeRepository.findAll().spliterator(), false).collect(toSet());
    }

    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id).orElseThrow(() -> new NotFoundException("Recipe not found. For Recipe ID " + id));
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {
        return recipeToRecipeCommand.convert(findById(id));
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("savedRecipe.getId() = " + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}
