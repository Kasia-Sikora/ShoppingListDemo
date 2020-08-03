package pl.sikora.katarzyna.ShoppingList.service;

import org.springframework.stereotype.Service;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingListProducts;
import pl.sikora.katarzyna.ShoppingList.model.UsersShoppingList;
import pl.sikora.katarzyna.ShoppingList.repository.ShoppingListProductsRepository;
import pl.sikora.katarzyna.ShoppingList.repository.UsersShoppingListRepository;

import java.util.List;

@Service
public class ShoppingListProductsService {

    private final ShoppingListProductsRepository repository;
    private final UsersShoppingListRepository usersShoppingListRepository;

    ShoppingListProductsService(ShoppingListProductsRepository repository,
                                UsersShoppingListRepository usersShoppingListRepository) {
        this.repository = repository;
        this.usersShoppingListRepository = usersShoppingListRepository;
    }

    public List<ShoppingListProducts> findAllByUserId(Long listId) {
        return this.repository.findAllByList_Id(listId);
    }

    public List<ShoppingListProducts> addList(List<ShoppingListProducts> list, Long list_id) {
        UsersShoppingList userList = usersShoppingListRepository.getOne(list_id);
        for (ShoppingListProducts product: list) {
            ShoppingListProducts tempProduct = getProductIfExist(list_id, product.getProduct_name(), product.getUnit());
            if(tempProduct != null){
                updateProduct(tempProduct, product.getQuantity());
            }else {
                product.setList(userList);
                this.repository.save(product);
            }
        }
        return this.repository.findAllByList_Id(list_id);
    }

    private void updateProduct(ShoppingListProducts tempProduct, Float quantity) {
        ShoppingListProducts existingList = this.repository.getOne(tempProduct.getId());
        existingList.setQuantity(existingList.getQuantity() + quantity);
        this.repository.saveAndFlush(existingList);
    }

    private ShoppingListProducts getProductIfExist(Long list_id, String product_name, String unit) {
        return this.repository.findByListIdAndProduct_nameLikeAndUnit(list_id, product_name, unit);
    }
}
