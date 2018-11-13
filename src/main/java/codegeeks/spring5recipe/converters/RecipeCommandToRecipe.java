package codegeeks.spring5recipe.converters;

import codegeeks.spring5recipe.commands.RecipeCommand;
import codegeeks.spring5recipe.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
    private final NotesCommandToNotes notesCommandToNotes;
    private final CategoryCommandToCategory categoryCommandToCategory;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    public RecipeCommandToRecipe(NotesCommandToNotes notesCommandToNotes, CategoryCommandToCategory categoryCommandToCategory, IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.notesCommandToNotes = notesCommandToNotes;
        this.categoryCommandToCategory = categoryCommandToCategory;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @Synchronized
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) {
            return null;
        }
        final Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setDescription(source.getDescription());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setCookTime(source.getCookTime());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setDirections(source.getDirections());
        source.getIngredients().forEach(ingredientCommand -> recipe.addIngredient(ingredientCommandToIngredient.convert(ingredientCommand)));
        recipe.setDifficulty(source.getDifficulty());
        recipe.setImage(source.getImage());
        recipe.setNotes(notesCommandToNotes.convert(source.getNotes()));
        recipe.setCategories(source.getCategories().stream().map(categoryCommandToCategory::convert).collect(Collectors.toSet()));
        return recipe;
    }
}
