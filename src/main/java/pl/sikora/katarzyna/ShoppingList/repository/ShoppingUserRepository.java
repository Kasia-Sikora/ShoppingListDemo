package pl.sikora.katarzyna.ShoppingList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingUser;
import pl.sikora.katarzyna.ShoppingList.service.ShoppingUserProjection;

@Repository
public interface ShoppingUserRepository extends JpaRepository<ShoppingUser, Long> {

    boolean existsShoppingUserByEmail(String email);

    ShoppingUserProjection getShoppingUserByEmail(String email);

    ShoppingUser getShoppingUserById(Long id);

    ShoppingUserProjection findShoppingUserById(Long id);
}
