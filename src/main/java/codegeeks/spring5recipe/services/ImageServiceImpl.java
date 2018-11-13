package codegeeks.spring5recipe.services;

import codegeeks.spring5recipe.domain.Recipe;
import codegeeks.spring5recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {
        try {
            log.debug("save image file for recipe id " + recipeId);
            Recipe recipe = recipeRepository.findById(recipeId).get();
            recipe.setImage(toWrappedByteArray(file.getBytes()));
            recipeRepository.save(recipe);
        } catch (IOException e) {
            log.error("error saving image for recipe id " + recipeId);
        }
    }

    private static Byte[] toWrappedByteArray(byte[] source) {
        Byte[] destination = new Byte[source.length];
        int i = 0;
        for (byte b : source) {
            destination[i++] = b;
        }
        return destination;
    }
}
