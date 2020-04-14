package pl.sikora.katarzyna.ShoppingList.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CollectionId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;


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
//    @NotEmpty
//    @NotBlank
    @Column(name = "user_id")
    private Long user_id;

//    @ManyToOne
//    @JoinTable(name="users")

    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "method")
    private String method;

    private String picture;

    public UsersRecipe(Long userId, String method, String picture) {
        this.user_id = userId;
        this.method = method;
        this.picture = picture;
    }
}


