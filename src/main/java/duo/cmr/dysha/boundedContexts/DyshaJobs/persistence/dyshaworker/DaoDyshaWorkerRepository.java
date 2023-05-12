package duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.dyshaworker;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface DaoDyshaWorkerRepository extends CrudRepository<DyshaWorkerEntity, Long> {
    DyshaWorkerEntity findByUserId(Long userId);
    boolean existsByUserId(Long userId);
}
