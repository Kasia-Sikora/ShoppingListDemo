package pl.sikora.katarzyna.ShoppingList.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "products_quantity")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductsQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String unit;

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Long product_id;

    private float quantity;

    private String department;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "user_recipe_id", updatable = false)
    @JsonBackReference(value = "productsQuantity")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UsersRecipe recipe;

    @OneToOne
    @JoinColumn(name = "product_id", updatable = false)
    private Product product;
}
