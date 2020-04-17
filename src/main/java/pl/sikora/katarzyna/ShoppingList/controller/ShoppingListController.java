package pl.sikora.katarzyna.ShoppingList.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingList;
import pl.sikora.katarzyna.ShoppingList.service.ShoppingListService;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ShoppingListController {

    private ShoppingListService service;

    @Autowired
    ShoppingListController(ShoppingListService service) { this.service = service; }

//    @GetMapping("/lists/{id}")
//    public Optional<ShoppingList> getAllByUserId(@PathVariable Long id){
//        return this.service.getAllShoppingListsByUserId(id);
//    }

    @PostMapping("/lists")
    public void addShoppingList(@RequestBody ShoppingList list){
        System.out.println(list);
        this.service.addList(list);
    }

    @DeleteMapping("/lists/{id}")
    public void deleteShoppingList(@PathVariable Long id){
        this.service.deleteList(id);
    }
}
