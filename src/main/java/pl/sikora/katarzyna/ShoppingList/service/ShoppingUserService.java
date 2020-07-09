package pl.sikora.katarzyna.ShoppingList.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingUser;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingUserProjection;
import pl.sikora.katarzyna.ShoppingList.repository.ShoppingUserRepository;
import pl.sikora.katarzyna.ShoppingList.util.errorHandlers.DataValidationException;
import pl.sikora.katarzyna.ShoppingList.util.security.passwordEncoder.PasswordEncoderInterface;

import javax.xml.bind.ValidationException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingUserService implements UserDetailsService {

    private final ShoppingUserRepository repository;
    private final PasswordEncoderInterface passwordEncoder;
    private final EmailSenderService emailSenderService;

    public ShoppingUserService(ShoppingUserRepository repository, PasswordEncoderInterface passwordEncoder, EmailSenderService emailSenderService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.emailSenderService = emailSenderService;
    }

    public List<ShoppingUser> getAllUsers() {
        return this.repository.findAll();
    }

    public ShoppingUser getUserById(Long id) {
        return this.repository.getShoppingUserById(id);
    }

    public ShoppingUser getUserByEmail(String email) {
        return this.repository.getShoppingUserByEmail(email);
    }

    public ShoppingUser addUser(ShoppingUser user) {
        if (!checkIfEmailExist(user.getEmail())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            this.repository.save(user);
            emailSenderService.setEmail(user);
            return this.repository.getShoppingUserByEmail(user.getEmail());
        } else {
            throw new DataValidationException("There is already user with this e-mail address");
        }
    }

    public ShoppingUser editUser(ShoppingUser user, Long id) {
        ShoppingUser existingUser = this.repository.getOne(id);
        BeanUtils.copyProperties(user, existingUser, "id");
        return this.repository.saveAndFlush(existingUser);
    }

    public void deleteUser(Long id) throws ValidationException {
        if (isUserIdExist(id)) {
            this.repository.deleteById(id);
        } else {
            throw new ValidationException("There is no user with this ID");
        }
    }

    public boolean isUserIdExist(Long id) {
        return this.repository.existsById(id);
    }

    public boolean checkIfEmailExist(String email) {
        return this.repository.existsShoppingUserByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ShoppingUserProjection user = repository.getShoppingUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new User(user.getEmail(), user.getPassword(), Collections.emptyList());
    }

    public ShoppingUserProjection activateUser(ShoppingUser user) {
        ShoppingUser existingUser = this.repository.getOne(user.getId());
        BeanUtils.copyProperties(user, existingUser, "id", "login", "password", "email");
        return this.repository.saveAndFlush(existingUser);
    }
}
