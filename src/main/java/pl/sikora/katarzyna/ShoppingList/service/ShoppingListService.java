package pl.sikora.katarzyna.ShoppingList.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingList;
import pl.sikora.katarzyna.ShoppingList.repository.ShoppingListRepository;

@Service
public class ShoppingListService {

    private final ShoppingListRepository repository;

    public ShoppingListService(ShoppingListRepository repository) {
        this.repository = repository;
    }

    public void addList(ShoppingList list) {
        System.out.println(list);
        this.repository.save(list);
    }

    public void deleteList(Long id) {
        this.repository.deleteById(id);
    }

    public ShoppingList editList(ShoppingList list, Long listId) {
        ShoppingList existingList = this.repository.getOne(listId);
        BeanUtils.copyProperties(list, existingList, "id");
        return this.repository.saveAndFlush(existingList);
    }
}
