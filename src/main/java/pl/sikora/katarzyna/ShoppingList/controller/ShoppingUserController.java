package pl.sikora.katarzyna.ShoppingList.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingUser;
import pl.sikora.katarzyna.ShoppingList.service.ShoppingUserService;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ShoppingUserController {

    private ShoppingUserService service;

    @Autowired
    public ShoppingUserController(ShoppingUserService service) {
        this.service = service;
    }


    @GetMapping("/users")
    public List<ShoppingUser> getAllUsers() {
        return this.service.getAllUsers();
    }

    @GetMapping("/users/{user_id}")
    public Object getUser(@PathVariable Long user_id) {
        if (this.service.isUserIdExist(user_id)) {
            return this.service.getUser(user_id);
        } else {
            return new ResponseEntity(user_id, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/users")
    public ShoppingUser addUser(@RequestBody ShoppingUser user) {
        return this.service.addUser(user);
    }

    @PutMapping("/users/{user_id}")
    public ResponseEntity<ShoppingUser> editUser(@RequestBody ShoppingUser user, @PathVariable Long user_id) throws ValidationException {
        if (this.service.isUserIdExist(user_id)) {
            return new ResponseEntity<>(this.service.editUser(user, user_id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) throws ValidationException {
        if (this.service.isUserIdExist(id)) {
            this.service.deleteUser(id);
        } else {
            throw new ValidationException("No user With this ID");
        }
    }
}
