package pl.sikora.katarzyna.ShoppingList.controller;


import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingUser;
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
    public ResponseEntity<ShoppingUser> checkRegisterForm(@Valid @RequestBody ShoppingUser user) throws DataValidationException {
        System.out.println(user);
        if (!checkIfEmailExist(user.getEmail())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            this.service.addUser(user);
            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        } else {
            System.out.println("DataValidationException: There is already user with this e-mail address");
            throw new DataValidationException("There is already user with this e-mail address");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<ShoppingUser> identifySelf(Principal principal){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getDetails());
        return new ResponseEntity<>(this.service.getUserByEmail(principal.getName()), HttpStatus.OK);
    }

//    @PostMapping("/login")
//    @ResponseBody
//    public ResponseEntity<ShoppingUser> checkLoginForm(@RequestBody ShoppingUser user) throws DataValidationException {
//        if (checkIfEmailExist(user.getEmail())) {
//            ShoppingUser existingUser = (ShoppingUser) getUserByEmail(user.getEmail());
//            if (passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
//                return new ResponseEntity<>(existingUser, HttpStatus.ACCEPTED);
//            }
//        }
//        System.out.println("Wrong login or password");
//        throw new DataValidationException("Wrong Login or password");
//    }

    //TODO remove object
    @GetMapping("/users/{user_id}")
    public Object getUserByEmail(@PathVariable Long user_id) {
        if (this.service.isUserIdExist(user_id)) {
            return this.service.getUser(user_id);
        } else {
            return new ResponseEntity(user_id, HttpStatus.BAD_REQUEST);
        }
    }

    public Object getUserByEmail(@PathVariable String email) {
        if (this.service.isUserEmailExist(email)) {
            return this.service.getUserByEmail(email);
        } else {
            return new ResponseEntity(email, HttpStatus.BAD_REQUEST);
        }
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
            throw new ValidationException("There is no user with this ID");
        }
    }

    public boolean checkIfEmailExist(String email) {
        return this.service.isUserEmailExist(email);
    }
}
