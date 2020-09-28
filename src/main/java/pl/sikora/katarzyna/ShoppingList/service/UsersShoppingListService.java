package pl.sikora.katarzyna.ShoppingList.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingUser;
import pl.sikora.katarzyna.ShoppingList.model.UsersShoppingList;
import pl.sikora.katarzyna.ShoppingList.repository.ShoppingUserRepository;
import pl.sikora.katarzyna.ShoppingList.repository.UsersShoppingListRepository;

import java.util.List;

@Service
public class UsersShoppingListService {

    private final UsersShoppingListRepository repository;
    private final ShoppingUserRepository userRepository;

    public UsersShoppingListService(UsersShoppingListRepository repository,
                                    ShoppingUserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public UsersShoppingList addList(UsersShoppingList list, Long userId) {
        if(listExist(userId, list.getTitle())){
            return this.repository.getByListOwnerIdAndTitle(userId, list.getTitle());
        }
        ShoppingUser user = this.userRepository.getShoppingUserById(userId);
        list.setListOwner(user);
        System.out.println(list);
        return this.repository.save(list);
    }

    private boolean listExist(Long userId, String title) {
        return this.repository.existsByListOwnerIdAndTitle(userId, title);
    }

    public void deleteList(Long id) {
        if(id != null) {
            this.repository.deleteById(id);
        }
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
