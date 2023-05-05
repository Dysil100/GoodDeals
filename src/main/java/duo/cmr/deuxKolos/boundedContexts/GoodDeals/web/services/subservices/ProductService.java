package duo.cmr.deuxKolos.boundedContexts.GoodDeals.web.services.subservices;

import duo.cmr.deuxKolos.boundedContexts.GoodDeals.domain.models.product.Product;
import duo.cmr.deuxKolos.boundedContexts.GoodDeals.web.services.interfaces.repositories.ProductRepository;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@org.springframework.stereotype.Service
public class ProductService {

    private ProductRepository productRepository;


    public void save(Product form) {
        productRepository.save(form);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> seachByExpr(String search) {
        return new SearchProductService().searchProductsByExpr(findAll(), search);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> searchRestByExprr(String query) {
        List<Product> searchliste = seachByExpr(query);
        return findAll().stream().filter(p -> !searchliste.contains(p)).collect(Collectors.toList());
    }
}
