package pl.sikora.katarzyna.ShoppingList.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingUser;
import pl.sikora.katarzyna.ShoppingList.model.UsersRecipe;
import pl.sikora.katarzyna.ShoppingList.repository.ShoppingUserRepository;
import pl.sikora.katarzyna.ShoppingList.repository.UsersRecipeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserRecipeService {

    private UsersRecipeRepository repository;

    public UserRecipeService(UsersRecipeRepository repository) {
        this.repository = repository;
    }

    public UsersRecipe addRecipe(UsersRecipe recipe) {
        return this.repository.save(recipe);
    }

    public Optional<UsersRecipe> getRecipe(Long id) {
        return this.repository.findById(id);
    }

    public List<UsersRecipe> getAllRecipes() { return this.repository.findAll(); }

    public Object editRecipe(UsersRecipe recipe, Long recipe_id) {
        UsersRecipe existingRecipe = this.repository.getOne(recipe_id);
        BeanUtils.copyProperties(recipe, existingRecipe, "id");
        return this.repository.saveAndFlush(existingRecipe);
    }

    public void deleteRecipe(Long id) {
        this.repository.deleteById(id);
    }

    public boolean isRecipeIdExist(Long recipe_id) {
        return this.repository.existsById(recipe_id);
    }
}
