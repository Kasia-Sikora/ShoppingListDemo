package pl.sikora.katarzyna.ShoppingList.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    private String login;

    @NotNull
    @NotEmpty
    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull
    @NotEmpty
    @NotBlank
    private String email;


    public ShoppingUser(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }
}
