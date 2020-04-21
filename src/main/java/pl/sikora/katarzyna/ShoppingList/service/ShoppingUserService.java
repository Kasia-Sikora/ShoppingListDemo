package pl.sikora.katarzyna.ShoppingList.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingUser;
import pl.sikora.katarzyna.ShoppingList.repository.ShoppingUserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingUserService {

    private ShoppingUserRepository repository;

    public ShoppingUserService(ShoppingUserRepository repository) {
        this.repository = repository;
    }

    public ShoppingUser addUser(ShoppingUser user) {
        return this.repository.save(user);
    }

    public Optional<ShoppingUser> getUser(Long id) {
        return this.repository.findById(id);
    }

    public ShoppingUser getUserByEmail(String email) {
        return this.repository.getShoppingUserByEmail(email);
    }

//    public Optional<ShoppingUser> getUser(String login) {
//        return this.repository.findByLogin(login);
//    }

    public List<ShoppingUser> getAllUsers() {
        return this.repository.findAll();
    }

    public ShoppingUser editUser(ShoppingUser user, Long id) {
        ShoppingUser existingUser = this.repository.getOne(id);
        BeanUtils.copyProperties(user, existingUser, "id");
        return this.repository.saveAndFlush(existingUser);

    }

    public void deleteUser(Long id) {
        this.repository.deleteById(id);
    }

    public boolean isUserIdExist(Long id) {
        return this.repository.existsById(id);
    }

    public boolean isUserEmailExist(String email) {
        return this.repository.existsShoppingUserByEmail(email);
    }
}
