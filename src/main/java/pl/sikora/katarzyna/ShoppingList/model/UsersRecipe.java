package pl.sikora.katarzyna.ShoppingList.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @Column(name = "method")
    private String method;

    private String picture;

//
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
//    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    @JsonBackReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ShoppingUser recipeOwner;

//    public UsersRecipe(Long userId, String method, String picture) {
//        this.user_id = userId;
//        this.method = method;
//        this.picture = picture;
//    }
}


