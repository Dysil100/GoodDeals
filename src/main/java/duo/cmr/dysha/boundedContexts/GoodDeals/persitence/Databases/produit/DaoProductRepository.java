package duo.cmr.dysha.boundedContexts.GoodDeals.persitence.Databases.produit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DaoProductRepository extends CrudRepository<ProductEntity, Long> {

}
