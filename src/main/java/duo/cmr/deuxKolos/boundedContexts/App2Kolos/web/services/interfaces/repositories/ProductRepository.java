package duo.cmr.deuxKolos.boundedContexts.App2Kolos.web.services.interfaces.repositories;

import duo.cmr.deuxKolos.boundedContexts.App2Kolos.domaine.models.Product;

import java.util.List;

public interface ProductRepository {

    void save(Product product);

    Product findById(Long id);

    List<Product> findAll();

    void deleteById(Long id);

    List<Product> filterNach(String search);
}
