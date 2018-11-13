package codegeeks.spring5recipe.controllers;

import codegeeks.spring5recipe.commands.RecipeCommand;
import codegeeks.spring5recipe.services.ImageService;
import codegeeks.spring5recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Controller
public class ImageController {
    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{recipeId}/image")
    public String showImageForm(@PathVariable String recipeId, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));
        return "recipe/imageuploadform";
    }

    @PostMapping("/recipe/{recipeId}/image")
    public String saveImage(@PathVariable String recipeId, @RequestParam MultipartFile imageFile) {
        imageService.saveImageFile(Long.valueOf(recipeId), imageFile);
        return "redirect:/recipe/" + recipeId + "/show";
    }

    @GetMapping("/recipe/{recipeId}/recipeimage")
    public void renderImageFromDB(@PathVariable String recipeId, HttpServletResponse response) throws IOException {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
        InputStream is = new ByteArrayInputStream(toNativeByteArray(recipeCommand.getImage()));
        response.setContentType("image/jpeg");
        IOUtils.copy(is, response.getOutputStream());
    }

    private static byte[] toNativeByteArray(Byte[] source) {
        byte[] destination = new byte[source.length];
        int i = 0;
        for( Byte b : source) {
            destination[i++] = b;
        }
        return destination;
    }
}
