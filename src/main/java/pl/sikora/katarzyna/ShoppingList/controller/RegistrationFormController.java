package pl.sikora.katarzyna.ShoppingList.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sikora.katarzyna.ShoppingList.errorHandlers.DataValidationException;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingUser;
import pl.sikora.katarzyna.ShoppingList.util.passwordEncoder.PasswordEncoderInterface;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationFormController {

    private ShoppingUserController controller;

    private PasswordEncoderInterface passwordEncoder;

    @Autowired
    RegistrationFormController(ShoppingUserController controller, PasswordEncoderInterface passwordEncoder) {
        this.controller = controller;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/users")
    @ResponseBody
    public ResponseEntity<String> checkRegisterForm(@Valid ShoppingUser user) throws DataValidationException {
        System.out.println(user);
        if (!controller.checkIfEmailExist(user.getEmail())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            controller.addUser(user);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            System.out.println("DataValidationException: There is already user with that e-mail address");
            throw new DataValidationException("There is already user with that e-mail address");
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Object> checkLoginForm(@Valid String email, String password) throws DataValidationException {
        if (controller.checkIfEmailExist(email)) {
            ShoppingUser existingUser = (ShoppingUser) controller.getUser(email);
            if (passwordEncoder.matches(password, existingUser.getPassword())) {
                return new ResponseEntity<>(existingUser, HttpStatus.ACCEPTED);
            }
        }
        System.out.println("Wrong login or password");
        throw new DataValidationException("Wrong Login or password");
    }
}
