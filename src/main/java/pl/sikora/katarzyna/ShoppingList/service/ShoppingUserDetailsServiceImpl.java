package pl.sikora.katarzyna.ShoppingList.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingUser;
import pl.sikora.katarzyna.ShoppingList.repository.ShoppingUserRepository;

import java.util.Collections;

@Service
public class ShoppingUserDetailsServiceImpl implements UserDetailsService {

    private ShoppingUserRepository repository;

    public ShoppingUserDetailsServiceImpl(ShoppingUserRepository applicationUserRepository) {
        this.repository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ShoppingUser user = repository.getShoppingUserByEmail(email);
        if (StringUtils.isEmpty(email)) {
            throw new UsernameNotFoundException(email);
        }
        return new User(user.getEmail(), user.getPassword(), Collections.emptyList());
    }
}
