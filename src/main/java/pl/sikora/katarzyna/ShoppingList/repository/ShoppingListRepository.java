package pl.sikora.katarzyna.ShoppingList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingList;

import java.util.Optional;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

//    Optional<ShoppingList> findAllByUser_Id(Long userId);

}
