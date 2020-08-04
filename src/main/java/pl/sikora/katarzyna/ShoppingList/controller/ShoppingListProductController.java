package pl.sikora.katarzyna.ShoppingList.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingListProducts;
import pl.sikora.katarzyna.ShoppingList.service.ShoppingListProductsService;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://foodstuff.sikorakatarzyna.pl", "http://www.foodstuff.sikorakatarzyna.pl", "www.foodstuff.sikorakatarzyna.pl"})
public class ShoppingListProductController {

    private final ShoppingListProductsService service;

    @Autowired
    ShoppingListProductController(ShoppingListProductsService service) {
        this.service = service;
    }

    @GetMapping("{user_id}/shopping-products/{list_id}")
    public List<ShoppingListProducts> getAllUsersShoppingLists(@PathVariable Long list_id) {
        return this.service.findAllByUserId(list_id);
    }

    @PostMapping("/{user_id}/shopping-products/{list_id}")
    public List<ShoppingListProducts> addShoppingList(@PathVariable Long list_id, @RequestBody List<ShoppingListProducts> list) {
        System.out.println(list);
        return this.service.addList(list, list_id);
    }
}
