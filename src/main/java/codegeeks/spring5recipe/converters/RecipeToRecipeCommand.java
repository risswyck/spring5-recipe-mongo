package codegeeks.spring5recipe.converters;

import codegeeks.spring5recipe.commands.RecipeCommand;
import codegeeks.spring5recipe.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    private final NotesToNotesCommand notesToNotesCommand;
    private final CategoryToCategoryCommand categoryToCategoryCommand;
    private final IngredientToIngredientCommand ingredientCommandToIngredient;

    public RecipeToRecipeCommand(NotesToNotesCommand notesCommandToNotes, CategoryToCategoryCommand categoryCommandToCategory, IngredientToIngredientCommand ingredientCommandToIngredient) {
        this.notesToNotesCommand = notesCommandToNotes;
        this.categoryToCategoryCommand = categoryCommandToCategory;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null) {
            return null;
        }
        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(source.getId());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setIngredients(source.getIngredients().stream().map(ingredientCommandToIngredient::convert).collect(Collectors.toSet()));
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setNotes(notesToNotesCommand.convert(source.getNotes()));
        recipeCommand.setCategories(source.getCategories().stream().map(categoryToCategoryCommand::convert).collect(Collectors.toSet()));
        return recipeCommand;
    }
}
