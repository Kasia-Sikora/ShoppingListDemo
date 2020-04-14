package pl.sikora.katarzyna.ShoppingList.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pl.sikora.katarzyna.ShoppingList.model.UsersRecipe;
import pl.sikora.katarzyna.ShoppingList.service.UserRecipeService;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserRecipeController {

    private UserRecipeService service;

    @Autowired
    UserRecipeController(UserRecipeService service) {
        this.service = service;
    }


    @GetMapping("/recipes")
    public List<UsersRecipe> getAllRecipes() {
        return this.service.getAllRecipes();
    }

    @GetMapping("/recipes/{recipe_id}")
    public Object getUser(@PathVariable Long recipe_id) {
        if (this.service.isRecipeIdExist(recipe_id)) {
            return this.service.getRecipe(recipe_id);
        } else {
            return new ResponseEntity(recipe_id, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/recipes")
    public UsersRecipe addRecipe(@RequestBody UsersRecipe recipe) {
        return this.service.addRecipe(recipe);
    }

    @PutMapping("/recipes/{recipe_id}")
    public ResponseEntity<UsersRecipe> editRecipe(@RequestBody UsersRecipe recipe, @PathVariable Long recipe_id) throws ValidationException {
        if (this.service.isRecipeIdExist(recipe_id)) {
            return new ResponseEntity(this.service.editRecipe(recipe, recipe_id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(recipe, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/recipes/{recipe_id}")
    public void deleteUser(@PathVariable Long recipe_id) throws ValidationException {
        if (this.service.isRecipeIdExist(recipe_id)) {
            this.service.deleteRecipe(recipe_id);
        } else {
            throw new ValidationException("No user With this ID");
        }
    }

}
