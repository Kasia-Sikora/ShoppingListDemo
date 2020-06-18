package pl.sikora.katarzyna.ShoppingList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingUser;

@Repository
public interface ShoppingUserRepository extends JpaRepository<ShoppingUser, Long> {

    boolean existsShoppingUserByEmail(String email);

    ShoppingUser getShoppingUserByEmail(String email);

    ShoppingUser getShoppingUserById(Long id);

    ShoppingUser findShoppingUserById(Long id);
}
