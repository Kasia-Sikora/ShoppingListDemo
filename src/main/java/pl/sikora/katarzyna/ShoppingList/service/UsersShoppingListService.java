package pl.sikora.katarzyna.ShoppingList.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pl.sikora.katarzyna.ShoppingList.model.UsersShoppingList;
import pl.sikora.katarzyna.ShoppingList.repository.UsersShoppingListRepository;

import java.util.List;

@Service
public class UsersShoppingListService {

    private final UsersShoppingListRepository repository;

    public UsersShoppingListService(UsersShoppingListRepository repository) {
        this.repository = repository;
    }

    public void addList(UsersShoppingList list) {
        System.out.println(list);
        this.repository.save(list);
    }

    public void deleteList(Long id) {
        this.repository.deleteById(id);
    }

    public UsersShoppingList editList(UsersShoppingList list, Long listId) {
        UsersShoppingList existingList = this.repository.getOne(listId);
        BeanUtils.copyProperties(list, existingList, "id");
        return this.repository.saveAndFlush(existingList);
    }

    public List<UsersShoppingList> findAllByUserId(Long user_id) {
        return this.repository.findAllByListOwnerId(user_id);
    }
}
