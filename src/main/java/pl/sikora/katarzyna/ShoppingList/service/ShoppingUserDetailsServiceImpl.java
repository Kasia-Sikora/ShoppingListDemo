package pl.sikora.katarzyna.ShoppingList.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingUser;
import pl.sikora.katarzyna.ShoppingList.repository.ShoppingUserRepository;

import static java.util.Collections.emptyList;

@Service
public class ShoppingUserDetailsServiceImpl implements UserDetailsService {

    private ShoppingUserRepository applicationUserRepository;

    public ShoppingUserDetailsServiceImpl(ShoppingUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ShoppingUser applicationUser = applicationUserRepository.getShoppingUserByEmail(email);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(email);
        }
        return new User(applicationUser.getLogin(), applicationUser.getPassword(), emptyList());
    }
}
