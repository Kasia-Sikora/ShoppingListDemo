package pl.sikora.katarzyna.ShoppingList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sikora.katarzyna.ShoppingList.model.UsersRecipe;

@Repository
public interface UsersRecipeRepository extends JpaRepository<UsersRecipe, Long> {
}
