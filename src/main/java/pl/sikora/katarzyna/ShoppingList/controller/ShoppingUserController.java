package pl.sikora.katarzyna.ShoppingList.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingUser;
import pl.sikora.katarzyna.ShoppingList.service.ShoppingUserProjection;
import pl.sikora.katarzyna.ShoppingList.service.ShoppingUserService;
import pl.sikora.katarzyna.ShoppingList.util.errorHandlers.DataValidationException;
import pl.sikora.katarzyna.ShoppingList.util.security.passwordEncoder.PasswordEncoderInterface;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ShoppingUserController {

    private ShoppingUserService service;

    private PasswordEncoderInterface passwordEncoder;

    @Autowired
    public ShoppingUserController(ShoppingUserService service, PasswordEncoderInterface passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/users")
    public List<ShoppingUser> getAllUsers() {
        return this.service.getAllUsers();
    }

    @PostMapping("/sign-up")
    @ResponseBody
    public ResponseEntity<ShoppingUser> addUser(@Valid @RequestBody ShoppingUser user, Errors errors) throws DataValidationException {
        System.out.println(user);
        if(!errors.hasErrors()) {
            if (!checkIfEmailExist(user.getEmail())) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                this.service.addUser(user);
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                throw new DataValidationException("There is already user with this e-mail address");
            }
        }
        throw new DataValidationException("Invalid data");
    }

    @GetMapping("/me")
    @ResponseBody
    public ResponseEntity<ShoppingUserProjection> identifySelf(Principal principal) {
        ShoppingUserProjection user = this.service.getUserByEmail(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users/{user_id}")
    public ShoppingUser getUserById(@PathVariable Long user_id) {
        if (this.service.isUserIdExist(user_id)) {
            return this.service.getUserById(user_id);
        }
        return null;
    }

//    @GetMapping("/users/{email}")
//    public ShoppingUser getUserByEmail(@PathVariable String email) {
//        if (this.service.isUserEmailExist(email)) {
//            return this.service.getUserByEmail(email);
//        }
//        return new NoSuchElementException("There's no user with this e-mail address");
//    }

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
            throw new ValidationException("There is no user with this ID");
        }
    }

    public boolean checkIfEmailExist(String email) {
        return this.service.isUserEmailExist(email);
    }
}
