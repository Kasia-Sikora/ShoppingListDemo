package pl.sikora.katarzyna.ShoppingList.model;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "shopping_lists")
@EqualsAndHashCode(exclude = {"user"}, callSuper = false)
@EntityListeners(AuditingEntityListener.class)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

//    @NotBlank
//    @NotNull
//    @NotEmpty
//    Long user_id;

    Long product_id;

    Long shopping_list_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private ShoppingUser user;
}
