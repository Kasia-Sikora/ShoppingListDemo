package pl.sikora.katarzyna.ShoppingList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingListProducts;

import java.util.List;

@Repository
public interface ShoppingListProductsRepository extends JpaRepository<ShoppingListProducts, Long> {

    List<ShoppingListProducts> findAllByList_Id(Long id);

    @Query("select s from ShoppingListProducts as s where s.list.id = ?1 AND s.product_name = ?2 and s.unit = ?3")
    ShoppingListProducts findByListIdAndProduct_nameLikeAndUnit(Long list_id, String product_name, String unit);

}
