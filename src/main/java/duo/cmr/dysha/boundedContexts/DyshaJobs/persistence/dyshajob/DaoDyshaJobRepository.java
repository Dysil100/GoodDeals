package duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.dyshajob;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DaoDyshaJobRepository extends CrudRepository<DyshaJobEntity, Long> {
}
