package duo.cmr.deuxKolos.boundedContexts.App2Kolos.web.services.subservices;

import duo.cmr.deuxKolos.boundedContexts.App2Kolos.domaine.models.Product;
import duo.cmr.deuxKolos.boundedContexts.App2Kolos.web.services.interfaces.repositories.ProductRepository;
import lombok.AllArgsConstructor;

import java.util.List;


@AllArgsConstructor
@org.springframework.stereotype.Service
public class ProductService {

    private ProductRepository productRepository;


    public void save(Product form) {
        productRepository.save(form);
    }

    public List<Product> alle() {
        return productRepository.findAll();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> filterNach(String search) {
        return productRepository.filterNach(search);
    }
}
