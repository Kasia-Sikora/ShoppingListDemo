package pl.sikora.katarzyna.ShoppingList.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingUser;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationFormController {

    private ShoppingUserController controller;

    @Autowired
    RegistrationFormController(ShoppingUserController controller) {
        this.controller = controller;
    }

    @PostMapping("/users")
    @ResponseBody
    public void checkForm(@Valid ShoppingUser user) throws ValidationException {
        System.out.println(user);
        if (checkUserValidation(user)) {
            controller.addUser(user);
        } else {
            throw new ValidationException("Invalid data");
        }
    }

    //
//    @GetMapping("/users")
//    public String getAllUsers(Model model) {
//        model.addAttribute("users", controller.getAllUsers());
//        return "users";
//    }
//
    private boolean checkUserValidation(ShoppingUser user) {
        return user.getLogin() != null &&
                user.getPassword() != null &&
                user.getEmail() != null &&
                user.getEmail().contains("@");
    }
}
