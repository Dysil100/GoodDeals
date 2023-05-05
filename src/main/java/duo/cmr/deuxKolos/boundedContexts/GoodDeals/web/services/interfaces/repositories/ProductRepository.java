package duo.cmr.deuxKolos.boundedContexts.GoodDeals.web.services.interfaces.repositories;

import duo.cmr.deuxKolos.boundedContexts.GoodDeals.domain.models.product.Product;

import java.util.List;

public interface ProductRepository {

    void save(Product product);

    Product findById(Long id);

    List<Product> findAll();

    void deleteById(Long id);
}
