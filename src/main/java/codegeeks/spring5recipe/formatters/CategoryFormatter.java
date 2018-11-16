package codegeeks.spring5recipe.formatters;

import codegeeks.spring5recipe.commands.CategoryCommand;
import codegeeks.spring5recipe.services.CategoryService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class CategoryFormatter implements Formatter<CategoryCommand> {
    private final CategoryService categoryService;

    public CategoryFormatter(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public CategoryCommand parse(String text, Locale locale) throws ParseException {
        return categoryService.findByDescription(text).orElseThrow(() -> new ParseException("category not found: " + text, 0));
    }

    @Override
    public String print(CategoryCommand object, Locale locale) {
        return object.getDescription();
    }
}
