package duo.cmr.deuxKolos.boundedContexts.avis.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface DaoAvisRepository extends CrudRepository<AvisEntity, Long> {
}
