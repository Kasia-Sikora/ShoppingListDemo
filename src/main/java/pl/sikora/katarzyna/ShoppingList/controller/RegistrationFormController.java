package pl.sikora.katarzyna.ShoppingList.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingUser;
import pl.sikora.katarzyna.ShoppingList.service.ShoppingUserService;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.List;

@Controller
@EnableWebMvc
public class RegistrationFormController {

    private ShoppingUserController controller;

    @Autowired
    RegistrationFormController(ShoppingUserController controller) {
        this.controller = controller;
    }

    @PostMapping("/users")
    public String checkForm(@Valid @ModelAttribute ShoppingUser user, Model model) throws ValidationException{
        if (checkUserValidation(user)) {
            controller.addUser(user);
            model.addAttribute("user", controller.getUser(user.getId()));
            return "index";
        } else {
            throw new ValidationException("Invalid data");
        }
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", controller.getAllUsers());
        return "users";
    }

    private boolean checkUserValidation(ShoppingUser user) {
        return user.getLogin() != null &&
                user.getPassword() != null &&
                user.getEmail() != null &&
                user.getEmail().contains("@");
    }
}
