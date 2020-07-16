package pl.sikora.katarzyna.ShoppingList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sikora.katarzyna.ShoppingList.model.ProductsQuantity;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingList;

import java.util.List;

public interface ProductsQuantityRepository extends JpaRepository<ProductsQuantity, Long> {

    List<ProductsQuantity> getProductsQuantitiesByRecipeId(Long recipe_id);

    void deleteProductsQuantitiesByRecipeId(Long recipe_id);

    void deleteProductsQuantityById(Long id);

    boolean findProductsQuantityByRecipeId(Long recipe_id);

    ProductsQuantity getDistinctTopByRecipeId(Long recipe_id);
}
