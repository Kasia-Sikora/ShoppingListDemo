//package pl.sikora.katarzyna.ShoppingList.service;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import pl.sikora.katarzyna.ShoppingList.model.ConfirmationToken;
//import pl.sikora.katarzyna.ShoppingList.model.ShoppingUser;
//import pl.sikora.katarzyna.ShoppingList.model.ShoppingUserProjection;
//import pl.sikora.katarzyna.ShoppingList.repository.ConfirmationTokenRepository;
//import pl.sikora.katarzyna.ShoppingList.repository.ShoppingUserRepository;
//import pl.sikora.katarzyna.ShoppingList.util.errorHandlers.DataValidationException;
//
//import javax.xml.bind.ValidationException;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//class ShoppingUserServiceTest {
//
//    @Autowired
//    private ShoppingUserService service;
//
//    @MockBean
//    private ShoppingUserRepository repository;
//
//    @MockBean
//    private ConfirmationTokenRepository tokenRepository;
//
//    @Test
//    @DisplayName("Test getAllUsers Success")
//    void test_getAllUsers() {
//        ShoppingUser user1 = new ShoppingUser((long) 1, "Kasia", "Katarzyna", "kasia@kasia.com", false, null, null);
//        ShoppingUser user2 = new ShoppingUser((long) 2, "Piotr", "piotrek", "piotr@piotr.com", false, null, null);
//        Mockito.doReturn(Arrays.asList(user1, user2)).when(repository).findAll();
//
//        List<ShoppingUser> users = service.getAllUsers();
//        Assertions.assertEquals(2, users.size(), "GetAllUsers should return 2 users");
//    }
//
//    @Test
//    @DisplayName("Test getById Success")
//    void test_getUserById_Success() {
//        ShoppingUser mockUser = new ShoppingUser((long) 1, "Kasia", "kasia", "kasia@kasia.com", false, null, null);
//        Mockito.doReturn(mockUser).when(repository).getShoppingUserById((long) 1);
//
//        ShoppingUser returnedUser = service.getUserById((long) 1);
//
//        assertNotNull(returnedUser, "User should be present");
//        Assertions.assertEquals(returnedUser, mockUser, "Users should be the same");
//    }
//
//    @Test
//    @DisplayName("Test getById Failed")
//    void test_getUserById_Failed() {
//        Mockito.doReturn(null).when(repository).getShoppingUserById((long) 1);
//
//        ShoppingUser returnedUser = service.getUserById((long) 1);
//
//        assertNull(returnedUser, "User should not be present");
//    }
//
//    @Test
//    @DisplayName("Test getUserByEmail Success")
//    void testGetUserByEmail_Success() {
//        ShoppingUser mockUser = new ShoppingUser((long) 1, "Kasia", "kasia", "kasia@kasia.com", false, null, null);
//        Mockito.doReturn(mockUser).when(repository).getShoppingUserByEmail("kasia@kasia.com");
//        ShoppingUser returnedUser = service.getUserByEmail("kasia@kasia.com");
//
//        assertNotNull(returnedUser, "User should be present");
//        Assertions.assertEquals(returnedUser, mockUser, "Users should be the same");
//    }
//
//    @Test
//    @DisplayName("Test getUserByEmail Failed")
//    void testGetUserByEmail_Failed() {
//        ShoppingUser mockUser = new ShoppingUser((long) 1, "Kasia", "kasia", "kasia@kasia.com", false, null, null);
//        Mockito.doReturn(null).when(repository).getShoppingUserByEmail("kasia@kasia.com");
//        ShoppingUser returnedUser = service.getUserByEmail("kasia@kasia.com");
//
//        assertNull(returnedUser, "User should not be present");
//    }
//
//    @Test
//    @DisplayName("Test addUser Success")
//    void test_addUser_Success() {
//        ShoppingUser mockUser = new ShoppingUser((long) 1, "Kasia", "kasia", "kasia@kasia.com", false, null, null);
//        Mockito.doReturn(mockUser).when(repository).save(mockUser);
//        Mockito.doReturn(mockUser).when(repository).getShoppingUserByEmail("kasia@kasia.com");
//
//        ShoppingUser returnedUser = service.addUser(mockUser);
//
//        Assertions.assertNotNull(returnedUser, "The saved user should not be null");
//    }
//
//    @Test
//    @DisplayName("Test addUser Failed")
//    void test_addUser_Failed() {
//        ShoppingUser mockUser = new ShoppingUser((long) 1, "Kasia", "kasia", "kasia@kasia.com", false, null, null);
//        Mockito.doReturn(true).when(repository).existsShoppingUserByEmail("kasia@kasia.com");
//
//        Exception exception = assertThrows(DataValidationException.class, () -> {
//            service.addUser(mockUser);
//        });
//
//        String expectMessage = "There is already user with this e-mail address";
//        String actualMessage = exception.getMessage();
//        Assertions.assertTrue(actualMessage.contains(expectMessage));
//    }
//
//    @Test
//    @DisplayName("Test editUser Success")
//    void test_editUser_success() {
//        ShoppingUser mockUser = new ShoppingUser((long) 1, "Kasia", "kasia", "kasia@kasia.com", false, null, null);
//        ShoppingUser mockUser1 = new ShoppingUser((long) 1, "Kasia1", "kasia1", "kasia1@kasia.com", false, null, null);
//        Mockito.doReturn(mockUser).when(repository).getOne((long) 1);
//        Mockito.doReturn(mockUser).when(repository).saveAndFlush(mockUser);
//
//        ShoppingUser returnedUser = service.editUser(mockUser1, (long) 1);
//        System.out.println(returnedUser.toString());
//        Assertions.assertEquals(returnedUser, mockUser1, "The saved user should not be null");
//    }
//
//    @Test
//    @DisplayName("Test deleteUser Success")
//    void testDeleteUser_Success() throws ValidationException {
//        ShoppingUser mockUser = new ShoppingUser((long) 1, "Kasia", "kasia", "kasia@kasia.com", false, null, null);
//        Mockito.doReturn(mockUser).when(repository).saveAndFlush(mockUser);
//        Mockito.doReturn(true).when(repository).existsById((long) 1);
//
//        service.deleteUser((long) 1);
//        Assertions.assertNull(repository.getOne((long) 1), "User should not be present");
//    }
//
//    @Test
//    @DisplayName("Test deleteUser Throw ValidationException")
//    void test_DeleteUser_ThrowException() throws ValidationException {
//        Mockito.doReturn(false).when(repository).existsById((long) 1);
//
//        Exception exception = assertThrows(ValidationException.class, () -> {
//            service.deleteUser((long) 1);
//        });
//
//        String expectMessage = "There is no user with this ID";
//        String actualMessage = exception.getMessage();
//        Assertions.assertTrue(actualMessage.contains(expectMessage));
//    }
//
//    @Test
//    @DisplayName("Test loadUserByUsername Success")
//    void test_loadUserByUsername_Success() {
//        ShoppingUser mockUser = new ShoppingUser((long) 1, "Kasia", "kasia", "kasia@kasia.com", false, null, null);
//        Mockito.doReturn(mockUser).when(repository).getShoppingUserByEmail("kasia@kasia.com");
//
//        Assertions.assertEquals(service.loadUserByUsername(mockUser.getEmail()), new User(mockUser.getEmail(), mockUser.getPassword(), Collections.emptyList()));
//    }
//
//    @Test
//    @DisplayName("Test deleteUser Throw UsernameNotFoundException")
//    void test_loadUserByUsername_ThrowsException() {
//        ShoppingUser mockUser = new ShoppingUser((long) 1, "Kasia", "kasia", "kasia@kasia.com", false, null, null);
//        Mockito.doReturn(null).when(repository).getShoppingUserByEmail("kasia@kasia.com");
//
//        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
//            service.loadUserByUsername(mockUser.getEmail());
//        });
//    }
//
//    @Test
//    @DisplayName("Test activateUser Success")
//    void activateUser() {
//        ShoppingUser mockUser = new ShoppingUser((long) 1, "Kasia", "kasia", "kasia@kasia.com", false, null, null);
//        ConfirmationToken token = new ConfirmationToken(mockUser);
//        Mockito.doReturn(mockUser).when(repository).getShoppingUserByEmail("kasia@kasia.com");
//        tokenRepository.save(token);
//        Mockito.doReturn(token).when(tokenRepository).findByConfirmationToken(token.getConfirmationToken());
//        Mockito.doReturn(mockUser).when(repository).getOne((long) 1);
//        Mockito.doReturn(mockUser).when(repository).saveAndFlush(mockUser);
//
//        Assertions.assertFalse(mockUser.isVerified());
//
//        ShoppingUserProjection resultUser = service.activateUser(token.getConfirmationToken());
//
//        Assertions.assertTrue(resultUser.isVerified());
//    }
//
//    @Test
//    @DisplayName("Test activateUser Null")
//    void test_activateUser_ReturnNull() {
//        ShoppingUser mockUser = new ShoppingUser((long) 1, "Kasia", "kasia", "kasia@kasia.com", false, null, null);
//        ConfirmationToken token = new ConfirmationToken(mockUser);
//        Mockito.doReturn(mockUser).when(repository).getShoppingUserByEmail("kasia@kasia.com");
//        tokenRepository.save(token);
//        Mockito.doReturn(null).when(tokenRepository).findByConfirmationToken(token.getConfirmationToken());
//        Mockito.doReturn(mockUser).when(repository).getOne((long) 1);
//        Mockito.doReturn(mockUser).when(repository).saveAndFlush(mockUser);
//
//        Assertions.assertFalse(mockUser.isVerified());
//
//        ShoppingUserProjection resultUser = service.activateUser(token.getConfirmationToken());
//
//        Assertions.assertNull(resultUser);
//    }
//}
