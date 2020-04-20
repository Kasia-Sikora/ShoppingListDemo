package pl.sikora.katarzyna.ShoppingList.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sikora.katarzyna.ShoppingList.errorHandlers.DataValidationException;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingUser;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationFormController{

    private ShoppingUserController controller;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Autowired
    RegistrationFormController(ShoppingUserController controller) {
        this.controller = controller;
    }

    @PostMapping("/users")
    @ResponseBody
    public ResponseEntity<String> checkForm(@Valid ShoppingUser user) throws DataValidationException {
        System.out.println(user);
        if (controller.checkIfEmailExist(user.getEmail())) {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
            controller.addUser(user);
        } else {
            throw new DataValidationException("There is already user with that e-mail address");
        }
        return null;
    }

//
//    @GetMapping("/users")
//    public String getAllUsers(Model model) {
//        model.addAttribute("users", controller.getAllUsers());
//        return "users";
//    }
//
//    private boolean checkUserValidation(ShoppingUser user) {
//        return user.getLogin() != null &&
//                user.getPassword() != null &&
//                user.getEmail() != null;
//    }
}
