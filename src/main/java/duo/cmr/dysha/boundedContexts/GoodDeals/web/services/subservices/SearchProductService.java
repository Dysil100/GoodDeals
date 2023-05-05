package duo.cmr.dysha.boundedContexts.GoodDeals.web.services.subservices;

import duo.cmr.dysha.boundedContexts.GoodDeals.domain.models.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchProductService {

    public List<Product> searchProductsByExpr(List<Product> all, String query) {
        Map<Product, Integer> scores = new HashMap<>(); // stocker le score pour chaque produit
        // parcourir tous les produits et calculer le score
        for (Product p : all) {
            int score = 0;
            if (p.getTitre().contains(query)) {
                score += 5; // titre est important, mais moins que le prix, on ajoute un score de 3
            }
            if (p.getDescription().contains(query)) {
                score += 3; // description est moins important que le titre, on ajoute un score de 1
            }
            if (p.getCity().contains(query)) {
                score += 1; // ville est plus important que description, on ajoute un score de 2
            }
            if (p.getUserEmail().contains(query)) {
                score += 4; // createur est moins important que date de publication, on ajoute un score de 1
            }
            if (p.getQuartier().contains(query)) {
                score += 2; // quartier est plus important que createur, on ajoute un score de 2
            }

            if (score > 0) scores.put(p, score); // ajouter le score seulement si le mot-clé est présent
        }

        // trier les Products par ordre décroissant de score
        List<Product> resultat = new ArrayList<>(scores.keySet());
        resultat.sort((p1, p2) -> scores.get(p2) - scores.get(p1));

        return resultat;
    }

}
