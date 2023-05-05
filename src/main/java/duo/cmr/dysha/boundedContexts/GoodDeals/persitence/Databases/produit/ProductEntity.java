package duo.cmr.dysha.boundedContexts.GoodDeals.persitence.Databases.produit;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table("product")
public class ProductEntity {

    @Id
    private Long id;

    private String userEmail;
    private String title;
    private String description;
    private Double price;
    private Boolean active;
    private Boolean vente;
    private String city;
    private String quartier;
    private String image;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public ProductEntity(String userEmail, String title, String description, Double price, Boolean active, boolean vente, String city,
                         String quartier, String image, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userEmail = userEmail;
        this.title = title;
        this.description = description;
        this.price = price;
        this.active = active;
        this.vente = vente;
        this.city = city;
        this.quartier = quartier;
        this.image = image;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
