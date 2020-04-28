package pl.sikora.katarzyna.ShoppingList.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingUser;
import pl.sikora.katarzyna.ShoppingList.service.ShoppingUserService;

import javax.xml.bind.ValidationException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
class ShoppingUserControllerTest {

    @Autowired
    private ShoppingUserController shoppingUserController;

    @MockBean
    private ShoppingUserService service;

    @Test
    void getAllUsersTest() {
        Mockito.when(service.getAllUsers()).thenReturn(Stream
                .of(new ShoppingUser((long) 1, "Kasia", "Katarzyna", "kasia@kasia.com"),
                        new ShoppingUser((long) 2, "Piotr", "piotrek", "piotr@piotr.com"))
                .collect(Collectors.toList()));
        Assertions.assertEquals(2, shoppingUserController.getAllUsers().size());
    }

    @Test
    public void addUserTest() {
        ShoppingUser user = new ShoppingUser((long) 1, "Christopher", "Moore", "wdd@khef.pl");
        Mockito.when(service.addUser(user)).thenReturn(user);
        Assertions.assertEquals(new ResponseEntity<>(user, HttpStatus.ACCEPTED), shoppingUserController.addUser(user));
    }

    @Test
    void identifySelf() {
    }

//    @Test
//    void getUserByEmailTest() {
//        ShoppingUser user = new ShoppingUser((long) 1, "Kasia", "Katarzyna", "kasia@kasia.com");
//        Mockito.when(service.getUserByEmail(user.getEmail())).thenReturn(user);
//        Mockito.when(service.isUserEmailExist(user.getEmail())).thenReturn(true);
//        Assertions.assertEquals(user, shoppingUserController.getUserByEmail(user.getEmail()));
//    }

//    @Test
//    void getUserByEmailTest_Fail() {
//        ShoppingUser user = new ShoppingUser((long) 1, "Kasia", "Katarzyna", "kasia@kasia.com");
//        Mockito.when(service.getUserByEmail(user.getEmail())).thenReturn(user);
//        Mockito.when(service.isUserEmailExist(user.getEmail())).thenReturn(false);
//        Assertions.assertEquals(new ResponseEntity(user.getEmail(), HttpStatus.BAD_REQUEST), shoppingUserController.getUserByEmail(user.getEmail()));
//    }

    @Test
    void getUserByIdTest() {
        ShoppingUser user = new ShoppingUser((long) 1, "Kasia", "Katarzyna", "kasia@kasia.com");
        Mockito.when(service.getUser(user.getId())).thenReturn(java.util.Optional.of(user));
        Assertions.assertEquals(user, shoppingUserController.getUserById(user.getId()));
    }

    @Test
    void editUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void checkIfEmailExist() {
    }
}
