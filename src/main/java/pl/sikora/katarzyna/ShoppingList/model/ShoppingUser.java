package pl.sikora.katarzyna.ShoppingList.model;


import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.common.reflection.XProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

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

    @NotNull(message = "Login has to be min 4")
    @Size(min = 4, max = 15)
    private String login;

    @NotNull(message = "Password has to be min 6")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 6)
    private String password;

    @NotNull
    @Email(message = "E-mail has to be correct")
    private String email;


    public ShoppingUser(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
//    @JsonIdentityInfo(
//            generator = ObjectIdGenerators.PropertyGenerator.class,
//            property = "id")
    private List<ShoppingList> shoppingLists = new ArrayList<>();

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "recipeOwner")
//    @JsonBackReference
//    @JsonIdentityInfo(
//            generator = ObjectIdGenerators.PropertyGenerator.class,
//            property = "user_id")
//    private List<UsersRecipe> recipes = new ArrayList<>();
}
