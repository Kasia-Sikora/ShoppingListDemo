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
@CrossOrigin(origins = {"http://localhost:4200", "http://foodstuff.sikorakatarzyna.pl", "www.foodstuff.sikorakatarzyna.pl"})
public class UserRecipeController {

    private final UserRecipeService service;

    @Autowired
    UserRecipeController(UserRecipeService service) {
        this.service = service;
    }

    @GetMapping("/{user_id}/recipes")
    public List<UsersRecipe> getAllUsersRecipes(@PathVariable Long user_id) {
        return this.service.findAllByUserId(user_id);
    }

//    @GetMapping("/recipes")
//    public List<UsersRecipe> getAllRecipes() {
//        return this.service.getAllRecipes();
//    }


    @GetMapping("/{user_id}/recipes/{recipe_id}")
    public ResponseEntity<Object> getRecipe(@PathVariable Long recipe_id, @PathVariable String user_id) {
        if (this.service.isRecipeIdExist(recipe_id)) {
            return new ResponseEntity<>(this.service.getRecipe(recipe_id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(recipe_id, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{user_id}/recipes")
    public UsersRecipe addRecipe(@RequestBody UsersRecipe recipe, @PathVariable Long user_id) {
        return this.service.addRecipe(recipe, user_id);
    }

    @PutMapping("/{user_id}/recipes/{recipe_id}")
    public ResponseEntity<UsersRecipe> editRecipe(@RequestBody UsersRecipe recipe, @PathVariable Long recipe_id, @PathVariable String user_id) throws ValidationException {
        System.out.println(recipe);
        if (this.service.isRecipeIdExist(recipe_id)) {
            return new ResponseEntity<>(this.service.editRecipe(recipe, recipe_id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(recipe, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{user_id}/recipes/{recipe_id}")
    public ResponseEntity<String> deleteUserRecipe(@PathVariable Long recipe_id, @PathVariable Long user_id) throws ValidationException {
        if (this.service.isRecipeIdExist(recipe_id)) {
            this.service.deleteRecipe(recipe_id, user_id);
            return new ResponseEntity<>("Recipe successful deleted", HttpStatus.NO_CONTENT);
        } else {
            throw new ValidationException("No recipe With this ID");
        }
    }
}
