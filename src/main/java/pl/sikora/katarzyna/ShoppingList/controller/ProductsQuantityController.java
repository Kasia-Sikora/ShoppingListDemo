package pl.sikora.katarzyna.ShoppingList.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sikora.katarzyna.ShoppingList.model.ProductsQuantity;
import pl.sikora.katarzyna.ShoppingList.service.ProductService;
import pl.sikora.katarzyna.ShoppingList.service.ProductsQuantityService;
import pl.sikora.katarzyna.ShoppingList.service.UserRecipeService;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://foodstuff.sikorakatarzyna.pl", "http://www.foodstuff.sikorakatarzyna.pl"})
public class ProductsQuantityController {

    private final ProductsQuantityService service;
    private final UserRecipeService recipeService;
    private final ProductService productService;

    @Autowired
    ProductsQuantityController(ProductsQuantityService service, UserRecipeService recipeService, ProductService productService) {
        this.service = service;
        this.recipeService = recipeService;
        this.productService = productService;
    }

    @PostMapping("/{recipe_id}/recipe_products")
    public void addProductQuantity(@RequestBody List<ProductsQuantity> productsQuantities, @PathVariable Long recipe_id) {
        this.service.addRecipeProduct(productsQuantities, recipe_id);
    }

    @GetMapping("/{recipe_id}/recipe_products")
    public List<ProductsQuantity> getProductQuantity(@PathVariable Long recipe_id) {
        return this.service.getRecipeProducts(recipe_id);
    }
}
