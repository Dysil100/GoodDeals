package duo.cmr.deuxKolos.boundedContexts.App2Kolos.persitence.Databases.service;

import duo.cmr.deuxKolos.boundedContexts.App2Kolos.domaine.models.Product;
import duo.cmr.deuxKolos.boundedContexts.App2Kolos.web.services.interfaces.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private DaoProductRepository daoProductRepository;
    @Override
    public void save(Product product) {
        daoProductRepository.save(toEntity(product));
    }

    @Override
    public Product findById(Long id) {
        return toService(daoProductRepository.findById(id).get());
    }

    @Override
    public List<Product> findAll() {
         return toServiceList(daoProductRepository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        daoProductRepository.deleteById(id);
    }

    @Override
    public List<Product> filterNach(String search) {
        return findAll().stream().filter(p -> p.getDescription().contains(search)||
                p.getUserEmail().contains(search)||p.getTitre().contains(search)).collect(Collectors.toList());
    }

    private List<Product> toServiceList(Iterable<ProductEntity> serviceEntities) {
        ArrayList<Product> Products = new ArrayList<>();
        serviceEntities.forEach(this::toService);
        return Products;
    }

    private Product toService(ProductEntity e) {
        return new Product(e.getId(), e.getUserEmail(), e.getTitle(), e.getDescription(), e.getPrice(), e.getActive());
    }

    private ProductEntity toEntity(Product p){
        return  new ProductEntity(p.getUserEmail(), p.getTitre(), p.getDescription(), p.getPrix(), p.isActive());
    }

}
