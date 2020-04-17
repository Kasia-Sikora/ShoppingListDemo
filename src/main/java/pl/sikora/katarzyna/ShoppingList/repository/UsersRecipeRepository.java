package pl.sikora.katarzyna.ShoppingList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sikora.katarzyna.ShoppingList.model.UsersRecipe;

import java.util.Optional;

@Repository
public interface UsersRecipeRepository extends JpaRepository<UsersRecipe, Long> {

    Optional<UsersRecipe> findAllByShoppingUser_Id(Long userId);
}
