package duo.cmr.dysha.boundedContexts.GoodDeals.persitence.Databases.produit;

import duo.cmr.dysha.boundedContexts.GoodDeals.domain.models.product.Product;
import duo.cmr.dysha.boundedContexts.GoodDeals.web.services.interfaces.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        return toProduct(daoProductRepository.findById(id).get());
    }

    @Override
    public List<Product> findAll() {
         return toProductList(daoProductRepository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        daoProductRepository.deleteById(id);
    }

    private List<Product> toProductList(Iterable<ProductEntity> serviceEntities) {
        ArrayList<Product> products = new ArrayList<>();
        serviceEntities.forEach(e -> products.add(toProduct(e)));
        return products;
    }

    private Product toProduct(ProductEntity e) {
        return new Product(e.getId(), e.getUserEmail(), e.getTitle(), e.getDescription(), e.getPrice(), e.getActive(),
                e.getVente(), e.getCity(), e.getQuartier(), e.getImage(), e.getCreatedAt(), e.getUpdatedAt());
    }

    private ProductEntity toEntity(Product p){
        p.setCreatedAt(p.getCreatedAt() == null ? LocalDateTime.now(): p.getCreatedAt());
        p.setUpdatedAt(p.getUpdatedAt() == null ? LocalDateTime.now(): p.getUpdatedAt());
        return  new ProductEntity(p.getUserEmail(), p.getTitre(), p.getDescription(), p.getPrix(), p.getActive(),
               p.getVente(), p.getCity(), p.getQuartier(), p.getImage(), p.getCreatedAt(), p.getUpdatedAt());
    }

}
