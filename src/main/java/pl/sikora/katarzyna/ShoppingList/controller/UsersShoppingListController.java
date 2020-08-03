package pl.sikora.katarzyna.ShoppingList.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sikora.katarzyna.ShoppingList.model.UsersRecipe;
import pl.sikora.katarzyna.ShoppingList.model.UsersShoppingList;
import pl.sikora.katarzyna.ShoppingList.service.UsersShoppingListService;

import java.util.List;


//TODO add methods after join users table and products table
@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://foodstuff.sikorakatarzyna.pl", "http://www.foodstuff.sikorakatarzyna.pl"})
public class UsersShoppingListController {

    private final UsersShoppingListService service;

    @Autowired
    UsersShoppingListController(UsersShoppingListService service) {
        this.service = service;
    }

    @GetMapping("/{user_id}/shopping-list")
    public List<UsersShoppingList> getAllUsersShoppingLists(@PathVariable Long user_id) {
        return this.service.findAllByUserId(user_id);
    }

    @PostMapping("/{user_id}/shopping-list")
    public UsersShoppingList addShoppingList(@PathVariable Long user_id, @RequestBody UsersShoppingList list) {
        return this.service.addList(list, user_id);
    }

    @DeleteMapping("/{user_id}/shopping-list/{id}")
    public void deleteShoppingList(@PathVariable Long id) {
        this.service.deleteList(id);
    }
}
