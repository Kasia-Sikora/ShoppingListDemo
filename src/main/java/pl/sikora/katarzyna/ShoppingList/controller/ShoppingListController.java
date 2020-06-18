package pl.sikora.katarzyna.ShoppingList.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingList;
import pl.sikora.katarzyna.ShoppingList.service.ShoppingListService;


//TODO add methods after join users table and products table
@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://foodstuff.sikorakatarzyna.pl", "http://www.foodstuff.sikorakatarzyna.pl"})
public class ShoppingListController {

    private final ShoppingListService service;

    @Autowired
    ShoppingListController(ShoppingListService service) {
        this.service = service;
    }

//    @PostMapping("/lists")
    public void addShoppingList(@RequestBody ShoppingList list) {
        System.out.println(list);
        this.service.addList(list);
    }

//    @DeleteMapping("/lists/{id}")
    public void deleteShoppingList(@PathVariable Long id) {
        this.service.deleteList(id);
    }
}
