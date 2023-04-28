package duo.cmr.deuxKolos.boundedContexts.App2Kolos.domaine.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Product {

    private Long id;
    private String userEmail;
    private String titre;
    private String description;
    private double prix;
    private boolean active;

    public Product(String userEmail, String titre, String description, double prix, boolean active) {
        this.userEmail = userEmail;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.active = active;
    }

    public void setEmail(String email) {
        this.userEmail = email;
    }
}
