package pl.sikora.katarzyna.ShoppingList.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


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

    private boolean is_favourite = false;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    @JsonBackReference(value = "recipes")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ShoppingUser recipeOwner;

    @OneToMany(mappedBy = "recipe")
    @JsonManagedReference(value = "productsQuantity")
    private List<ProductsQuantity> productsQuantity;

}


