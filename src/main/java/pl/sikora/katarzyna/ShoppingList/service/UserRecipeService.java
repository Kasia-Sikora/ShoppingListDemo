package pl.sikora.katarzyna.ShoppingList.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingUser;
import pl.sikora.katarzyna.ShoppingList.model.UsersRecipe;
import pl.sikora.katarzyna.ShoppingList.repository.UsersRecipeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserRecipeService {

    private final UsersRecipeRepository repository;
    private final ShoppingUserService userService;

    public UserRecipeService(UsersRecipeRepository repository, ShoppingUserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public UsersRecipe addRecipe(UsersRecipe recipe, Long user_id) {
        ShoppingUser user = userService.getUserById(user_id);
        recipe.setRecipeOwner(user);
        return this.repository.save(recipe);
    }

    public UsersRecipe getRecipe(Long id) {
        return this.repository.getOne(id);
    }

    public List<UsersRecipe> getAllRecipes(){
        return this.repository.findAll();
    }

    public UsersRecipe editRecipe(UsersRecipe recipe, Long recipe_id) {
        UsersRecipe existingRecipe = this.repository.getOne(recipe_id);
        BeanUtils.copyProperties(recipe, existingRecipe, "id", "recipeOwner");
        return this.repository.saveAndFlush(existingRecipe);
    }

    public void deleteRecipe(Long recipe_id, Long user_id) {
        UsersRecipe recipe = getRecipe(recipe_id);
        this.userService.getUserById(user_id)
                .getRecipes()
                .remove(recipe);
        recipe.setRecipeOwner(null);
        this.repository.deleteById(recipe_id);
    }

    public boolean isRecipeIdExist(Long recipe_id) {
        return this.repository.existsById(recipe_id);
    }

    public List<UsersRecipe> findAllByUserId(Long user_id) {
        if(isRecipeOwnerExist(user_id)) {
            return this.repository.findAllByRecipeOwnerId(user_id);
        }
        return null;
    }

    public boolean isRecipeOwnerExist(Long user_id) {
        return this.repository.existsByRecipeOwnerId(user_id);
    }

}
