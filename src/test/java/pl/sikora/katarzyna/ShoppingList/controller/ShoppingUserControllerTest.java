//package pl.sikora.katarzyna.ShoppingList.controller;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//import pl.sikora.katarzyna.ShoppingList.model.ShoppingUser;
//
//import javax.xml.bind.ValidationException;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//class ShoppingUserControllerTest {
//
//    @Autowired
//    ShoppingUserController shoppingUserController;
//
//
//    @Test
//    public void testCreateReadDelete() throws ValidationException {
//        ShoppingUser user = new ShoppingUser("Christopher", "Moore", "wdd@khef.pl");
//
//        ShoppingUser userResult = shoppingUserController.addUser(user);
//
//        List<ShoppingUser> users = shoppingUserController.getAllUsers();
//        Assertions.assertTrue(users.contains(user));
//
//        shoppingUserController.deleteUser(userResult.getId());
//        assertEquals(new ResponseEntity(userResult.getId(), HttpStatus.BAD_REQUEST), shoppingUserController.getUser(userResult.getId()));
//    }
//
//}
