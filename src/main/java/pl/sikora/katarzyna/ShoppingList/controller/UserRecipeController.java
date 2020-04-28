package pl.sikora.katarzyna.ShoppingList.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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


    @GetMapping("/{user_id}/recipes")
    public List<UsersRecipe> getAllUsersRecipes(@PathVariable Long user_id) {
        if(this.service.isRecipeOwnerExist(user_id)) {
            return this.service.findAllByUserId(user_id);
        }
        return null;
    }

    @GetMapping("/recipes")
    public List<UsersRecipe> getAllRecipes() {
        return this.service.getAllRecipes();
    }

    @GetMapping("/{user_id}/recipes/{recipe_id}")
    public ResponseEntity getRecipe(@PathVariable Long recipe_id, @PathVariable String user_id) {
        if (this.service.isRecipeIdExist(recipe_id)) {
            return new ResponseEntity(this.service.getRecipe(recipe_id), HttpStatus.OK);
        } else {
            return new ResponseEntity(recipe_id, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{user_id}/recipes")
    public UsersRecipe addRecipe(@RequestBody UsersRecipe recipe, @PathVariable String user_id) {
        return this.service.addRecipe(recipe);
    }

    @PutMapping("/{user_id}/recipes/{recipe_id}")
    public ResponseEntity<UsersRecipe> editRecipe(@RequestBody UsersRecipe recipe, @PathVariable Long recipe_id, @PathVariable String user_id) throws ValidationException {
        if (this.service.isRecipeIdExist(recipe_id)) {
            return new ResponseEntity(this.service.editRecipe(recipe, recipe_id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(recipe, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{user_id}/recipes/{recipe_id}")
    public void deleteUser(@PathVariable Long recipe_id, @PathVariable String user_id) throws ValidationException {
        if (this.service.isRecipeIdExist(recipe_id)) {
            this.service.deleteRecipe(recipe_id);
        } else {
            throw new ValidationException("No user With this ID");
        }
    }

}
