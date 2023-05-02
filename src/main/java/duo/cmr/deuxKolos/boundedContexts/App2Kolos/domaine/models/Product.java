package duo.cmr.deuxKolos.boundedContexts.App2Kolos.domaine.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Product {

    private Long id;
    private String userEmail;
    private String titre;
    private String description;
    private double prix;
    private Boolean active;
    private Boolean vente;
    private String city;
    private String quartier;
    private String image;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product(String userEmail, String titre, String description, double prix, boolean active, boolean vente, String city,
                   String quartier, String image, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userEmail = userEmail;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.active = active;
        this.vente = vente;
        this.city = city;
        this.quartier = quartier;
        this.image = image;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
