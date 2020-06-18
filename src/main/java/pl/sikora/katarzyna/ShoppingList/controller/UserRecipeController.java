package pl.sikora.katarzyna.ShoppingList.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingUser;
import pl.sikora.katarzyna.ShoppingList.model.UsersRecipe;
import pl.sikora.katarzyna.ShoppingList.service.ShoppingUserService;
import pl.sikora.katarzyna.ShoppingList.service.UserRecipeService;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://foodstuff.sikorakatarzyna.pl", "http://www.foodstuff.sikorakatarzyna.pl"})
public class UserRecipeController {

    private UserRecipeService service;
    private ShoppingUserService userService;

    @Autowired
    UserRecipeController(UserRecipeService service, ShoppingUserService userService) {
        this.service = service;
        this.userService = userService;
    }


    @GetMapping("/{user_id}/recipes")
    public List<UsersRecipe> getAllUsersRecipes(@PathVariable Long user_id) {
        System.out.println(user_id);
        if (this.service.isRecipeOwnerExist(user_id)) {
//            System.out.println(this.service.findAllByUserId(user_id).toString());
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
    public UsersRecipe addRecipe(@RequestBody UsersRecipe recipe, @PathVariable Long user_id) {
        ShoppingUser user = userService.getUserById(user_id);
        recipe.setRecipeOwner(user);
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
    public void deleteUser(@PathVariable Long recipe_id, @PathVariable Long user_id) throws ValidationException {
        if (this.service.isRecipeIdExist(recipe_id)) {
            UsersRecipe recipe = this.service.getRecipe(recipe_id);
            this.service.deleteRecipe(recipe_id);
            this.userService.getUserById(user_id).getRecipes().remove(recipe);
            recipe.setRecipeOwner(null);
        } else {
            throw new ValidationException("No recipe With this ID");
        }
    }
}
