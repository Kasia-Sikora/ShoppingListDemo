package pl.sikora.katarzyna.ShoppingList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sikora.katarzyna.ShoppingList.model.ConfirmationToken;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {

    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
