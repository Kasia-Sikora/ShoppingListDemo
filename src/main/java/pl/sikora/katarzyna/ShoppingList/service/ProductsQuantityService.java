package pl.sikora.katarzyna.ShoppingList.service;

import org.springframework.stereotype.Service;
import pl.sikora.katarzyna.ShoppingList.model.Product;
import pl.sikora.katarzyna.ShoppingList.model.ProductsQuantity;
import pl.sikora.katarzyna.ShoppingList.model.UsersRecipe;
import pl.sikora.katarzyna.ShoppingList.repository.ProductsQuantityRepository;

import java.util.List;

@Service
public class ProductsQuantityService {

    private final ProductsQuantityRepository repository;
    private final UserRecipeService recipeService;
    private final ProductService productService;

    public ProductsQuantityService(ProductsQuantityRepository repository,
                                   UserRecipeService recipeService,
                                   ProductService productService) {
        this.repository = repository;
        this.recipeService = recipeService;
        this.productService = productService;
    }

    public void addRecipeProduct(List<ProductsQuantity> productsQuantities, Long recipe_id) {
        UsersRecipe recipe = this.recipeService.getRecipe(recipe_id);
        for (ProductsQuantity products : productsQuantities) {
            if (products.getUnit() == null) {
                products.setUnit("");
            }
            Product product = this.productService.getProductById(products.getProduct_id());
            products.setRecipe(recipe);
            products.setProduct(product);
            this.repository.save(products);
        }
        recipe.setProductsQuantity(productsQuantities);
    }

    public List<ProductsQuantity> getRecipeProducts(Long recipe_id) {
        return this.repository.getProductsQuantitiesByRecipeId(recipe_id);
    }
}
