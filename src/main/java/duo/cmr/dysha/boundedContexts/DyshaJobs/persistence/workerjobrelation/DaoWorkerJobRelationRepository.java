package duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.workerjobrelation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DaoWorkerJobRelationRepository extends CrudRepository<WorkerJobRelationEntity, Long> {

    Iterable<WorkerJobRelationEntity> findAllByJobId(Long jobId);
    Iterable<WorkerJobRelationEntity> findAllByWorkerId(Long jobId);
}
