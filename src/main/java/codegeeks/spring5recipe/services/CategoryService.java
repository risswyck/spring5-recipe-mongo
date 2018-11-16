package codegeeks.spring5recipe.services;

import codegeeks.spring5recipe.commands.CategoryCommand;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryCommand> findAll();
    Optional<CategoryCommand> findByDescription(String description);
}
