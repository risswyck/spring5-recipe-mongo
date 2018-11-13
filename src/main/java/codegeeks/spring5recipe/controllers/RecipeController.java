package codegeeks.spring5recipe.controllers;

import codegeeks.spring5recipe.commands.RecipeCommand;
import codegeeks.spring5recipe.exceptions.NotFoundException;
import codegeeks.spring5recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
public class RecipeController {
    public static final String RECIPEFORM = "recipe/recipeform";
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findById(new Long(id)));
        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return RECIPEFORM;
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(new Long(id)));
        return RECIPEFORM;
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id, Model model) {
        recipeService.deleteById(new Long(id));
        return "redirect:/";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult) {
        // command.setIngredients(recipeService.findCommandById(command.getId()).getIngredients());

        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.error(objectError.toString()));
            return RECIPEFORM;
        }
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/" + savedRecipeCommand.getId() + "/show";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFoundException(Exception e) {
        log.error(e.getMessage());
        ModelAndView mav = new ModelAndView("recipe/404Error");
        mav.addObject("exception", e);
        return mav;
    }

}
