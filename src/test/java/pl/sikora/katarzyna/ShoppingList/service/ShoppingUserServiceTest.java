package pl.sikora.katarzyna.ShoppingList.service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.MockingDetails;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingUser;
import pl.sikora.katarzyna.ShoppingList.repository.ShoppingUserRepository;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingUserServiceTest {

    @Autowired
    private ShoppingUserService service;

    @MockBean
    private ShoppingUserRepository repository;

    @Test
    public void getAllUsersTest() {
        Mockito.when(repository.findAll()).thenReturn(Stream
                .of(new ShoppingUser((long) 1, "Kasia", "Katarzyna", "kasia@kasia.com"),
                        new ShoppingUser((long) 2, "Piotr", "piotrek", "piotr@piotr.com"))
                .collect(Collectors.toList()));
        Assertions.assertEquals(2, service.getAllUsers().size());
    }

    @Test
    public void getAllUsersTest_Fails() {
        Mockito.when(repository.findAll()).thenReturn(Stream
                .of(new ShoppingUser((long) 1, "Kasia", "Katarzyna", "kasia@kasia.com"),
                        new ShoppingUser((long) 2, "Piotr", "piotrek", "piotr@piotr.com"))
                .collect(Collectors.toList()));
        Assertions.assertNotEquals(3, service.getAllUsers().size());
    }

//    @Test
//    public void getUserByEmailTest() {
//        String email = "kasia@kasia.com";
//        Mockito.when(repository.getShoppingUserByEmail(email)).thenReturn(
//                new ShoppingUser((long) 1, "Kasia", "Katarzyna", "kasia@kasia.com"));
//        Assertions.assertEquals("Kasia", service.getUserByEmail(email).getLogin());
//    }

    @Test
    public void getUserByEmailTest_NotFound() {
        String email = "kasia@kasia.com";
        Mockito.when(repository.getShoppingUserByEmail(email)).thenReturn(null);
        Assertions.assertNull(service.getUserByEmail(email));
    }

    @Test
    public void getUserByIdTest() {
        ShoppingUser user = new ShoppingUser((long) 1, "Daniel", "daniel", "daniel@daniel.com");
        Mockito.when(repository.findById(user.getId())).thenReturn(java.util.Optional.of(user));
        Assertions.assertTrue(service.getUser(user.getId()).isPresent());
    }

    @Test
    public void addUserTest() {
        ShoppingUser user = new ShoppingUser((long) 1, "Daniel", "daniel", "daniel@daniel.com");
        Mockito.when(repository.save(user)).thenReturn(user);
        Assertions.assertEquals(user, service.addUser(user));
    }

//    TODO add editUser Tests
//    @Test
//    public void editUserTest(){
//        ShoppingUser user = new ShoppingUser((long) 1, "Daniel", "daniel", "daniel@daniel.com");
//        Mockito.when(repository.save(user)).thenReturn(user);
//        Mockito.when(repository.findById(user.getId())).thenReturn(java.util.Optional.of(user));
//        ShoppingUser editUser = new ShoppingUser("Kasia", "Katarzyna", "kasia@kasia.com");
//        Assertions.assertEquals(new ShoppingUser((long) 1, "Kasia", "Katarzyna", "kasia@kasia.com"), service.editUser(editUser, user.getId()));
//    }

    @Test
    public void deleteUserTest() {
        ShoppingUser user = new ShoppingUser((long) 1, "Daniel", "daniel", "daniel@daniel.com");
        service.deleteUser(user.getId());
        Mockito.verify(repository, Mockito.times(1)).deleteById(user.getId());
    }

    @Test
    public void isUserIdExistTest() {
        ShoppingUser user = new ShoppingUser((long) 1, "Daniel", "daniel", "daniel@daniel.com");
        Mockito.when(repository.existsById(user.getId())).thenReturn(true);
        Assertions.assertTrue(service.isUserIdExist(user.getId()));
    }

    @Test
    public void isUserEmailExistTest() {
        String email = "kasia@kasia.com";
        Mockito.when(repository.existsShoppingUserByEmail(email)).thenReturn(true);
        Assertions.assertTrue(service.isUserEmailExist(email));
    }
}
