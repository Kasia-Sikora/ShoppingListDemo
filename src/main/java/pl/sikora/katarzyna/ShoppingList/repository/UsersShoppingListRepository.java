package pl.sikora.katarzyna.ShoppingList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sikora.katarzyna.ShoppingList.model.UsersShoppingList;

import java.util.List;

@Repository
public interface UsersShoppingListRepository extends JpaRepository<UsersShoppingList, Long> {

    List<UsersShoppingList> findAllByListOwnerId(Long id);

    boolean existsByListOwnerIdAndTitle(Long id, String title);

    UsersShoppingList getByListOwnerIdAndTitle(Long userId, String title);
}
