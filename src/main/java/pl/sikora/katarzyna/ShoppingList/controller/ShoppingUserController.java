package pl.sikora.katarzyna.ShoppingList.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.sikora.katarzyna.ShoppingList.model.ConfirmationToken;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingUser;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingUserProjection;
import pl.sikora.katarzyna.ShoppingList.repository.ConfirmationTokenRepository;
import pl.sikora.katarzyna.ShoppingList.service.ShoppingUserService;
import pl.sikora.katarzyna.ShoppingList.util.errorHandlers.DataValidationException;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://foodstuff.sikorakatarzyna.pl", "www.foodstuff.sikorakatarzyna.pl"})
public class ShoppingUserController {

    private final ShoppingUserService service;

    ConfirmationTokenRepository confirmationTokenRepository;


    @Autowired
    public ShoppingUserController(ShoppingUserService service,    ConfirmationTokenRepository confirmationTokenRepository) {
        this.service = service;
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    @GetMapping("/users")
    public List<ShoppingUser> getAllUsers() {
        return this.service.getAllUsers();
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ShoppingUserProjection> addUser(@Valid @RequestBody ShoppingUser user, Errors errors) throws DataValidationException {
        if (!errors.hasErrors()) {
            ShoppingUserProjection shoppingUser = this.service.addUser(user);
            return new ResponseEntity<>(shoppingUser, HttpStatus.OK);
        }
        throw new DataValidationException("Invalid data");
    }

    @GetMapping("/me")
    public ResponseEntity<ShoppingUserProjection> identifySelf(Principal principal) {
        ShoppingUserProjection user = this.service.getUserByEmail(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value="/activate")
    public ResponseEntity confirmUserAccount(@RequestBody String confirmationToken) {

        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            ShoppingUser user = this.service.getUserByEmail(token.getUser().getEmail());
            user.setEnabled(true);
            System.out.println("this user: " + user);
            System.out.println("this token " + token);
            return new ResponseEntity(this.service.activateUser(user), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/users/{user_id}")
    public ShoppingUser getUserById(@PathVariable Long user_id) {
        if (this.service.isUserIdExist(user_id)) {
            return this.service.getUserById(user_id);
        }
        return null;
    }

    @PutMapping("/users/{user_id}")
    public ResponseEntity<ShoppingUser> editUser(@RequestBody ShoppingUser user, @PathVariable Long user_id){
        if (this.service.isUserIdExist(user_id)) {
            return new ResponseEntity<>(this.service.editUser(user, user_id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) throws ValidationException {
        this.service.deleteUser(id);
    }
}
