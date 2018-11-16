package codegeeks.spring5recipe.services;

import codegeeks.spring5recipe.commands.CategoryCommand;
import codegeeks.spring5recipe.converters.CategoryToCategoryCommand;
import codegeeks.spring5recipe.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryToCategoryCommand categoryToCategoryCommand;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryToCategoryCommand categoryToCategoryCommand) {
        this.categoryRepository = categoryRepository;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
    }

    @Override
    public List<CategoryCommand> findAll() {
        return StreamSupport.stream(categoryRepository.findAll().spliterator(), false)
                .map(categoryToCategoryCommand::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryCommand> findByDescription(String description) {
        return categoryRepository.findByDescription(description).map(categoryToCategoryCommand::convert);
    }
}
