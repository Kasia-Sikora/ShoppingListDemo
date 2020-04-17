package pl.sikora.katarzyna.ShoppingList.model;

import lombok.*;
import org.hibernate.annotations.CollectionId;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "users_recipes")
@EqualsAndHashCode(exclude = {"shoppingUser"}, callSuper = false)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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
    private String title;

    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "method")
    private String method;

    private String picture;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private ShoppingUser shoppingUser;

    public UsersRecipe(Long userId, String method, String picture) {
        this.user_id = userId;
        this.method = method;
        this.picture = picture;
    }
}


