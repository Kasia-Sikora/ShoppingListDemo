package pl.sikora.katarzyna.ShoppingList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sikora.katarzyna.ShoppingList.model.ProductsQuantity;

import java.util.List;

@Repository
public interface ProductsQuantityRepository extends JpaRepository<ProductsQuantity, Long> {

    List<ProductsQuantity> getProductsQuantitiesByRecipeId(Long recipe_id);
}
