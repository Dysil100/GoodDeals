package duo.cmr.deuxKolos.boundedContexts.App2Kolos.persitence.Databases.service;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


import java.io.Serializable;

@Table("product")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class ProductEntity implements Serializable {
    @Id
    private Long id;

    private String userEmail;
    private String title;
    private String description;
    private Double price;
    private Boolean active;

    public ProductEntity(String userEmail, String title, String description, Double price, Boolean active) {
        this.userEmail = userEmail;
        this.title = title;
        this.description = description;
        this.price = price;
        this.active = active;
    }

    // Getters and setters
    // ...

}
