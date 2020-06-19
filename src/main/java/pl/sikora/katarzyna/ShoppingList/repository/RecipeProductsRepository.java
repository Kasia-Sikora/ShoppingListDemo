package pl.sikora.katarzyna.ShoppingList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingList;

public interface RecipeProductsRepository extends JpaRepository<ShoppingList, Long> {
}
