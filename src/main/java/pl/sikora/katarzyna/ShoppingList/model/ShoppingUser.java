package pl.sikora.katarzyna.ShoppingList.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingUser implements ShoppingUserProjection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Login must be at least 4 characters in length")
    @Size(min = 4, max = 15)
    private String login;

    @NotNull(message = "Passwords must be at least 6 characters in length")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 6)
    private String password;

    @NotNull
    @Email(message = "E-mail must be correct")
    private String email;


    //    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonBackReference
////    @JsonIdentityInfo(
////            generator = ObjectIdGenerators.PropertyGenerator.class,
////            property = "id")
//    private List<ShoppingList> shoppingLists = new ArrayList<>();
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true , mappedBy = "recipeOwner")
    @JsonManagedReference(value = "recipes")
    private List<UsersRecipe> recipes = new ArrayList<>();

}
