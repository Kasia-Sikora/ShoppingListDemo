package pl.sikora.katarzyna.ShoppingList.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingUser;

public interface ShoppingUserProjection {

    Long getId();

    String getLogin();

    String getEmail();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String getPassword();
}
