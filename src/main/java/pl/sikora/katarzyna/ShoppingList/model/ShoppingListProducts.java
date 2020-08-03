package pl.sikora.katarzyna.ShoppingList.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "shopping_list_products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingListProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    String product_name;

    String unit;

    Float quantity;

    String department;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_list_id", nullable = false, updatable = false)
    @JsonBackReference(value = "list")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UsersShoppingList list;
}
