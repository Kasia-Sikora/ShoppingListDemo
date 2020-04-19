package pl.sikora.katarzyna.ShoppingList.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "users_recipes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersRecipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    private String title;

    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "method")
    private String method;

    private String picture;

//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
//    @JsonManagedReference
//    private ShoppingUser recipeOwner;

//    public UsersRecipe(Long userId, String method, String picture) {
//        this.user_id = userId;
//        this.method = method;
//        this.picture = picture;
//    }
}


