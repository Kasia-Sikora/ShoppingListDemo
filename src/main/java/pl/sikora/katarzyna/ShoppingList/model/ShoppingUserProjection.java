package pl.sikora.katarzyna.ShoppingList.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface ShoppingUserProjection {

    Long getId();

    String getLogin();

    String getEmail();

    boolean isVerified();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String getPassword();
}
