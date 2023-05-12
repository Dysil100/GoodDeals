package duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.workerjobrelation;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DaoWorkerJobRelationRepository extends CrudRepository<WorkerJobRelationEntity, Long> {

    @Transactional
    @Query(value = "SELECT * FROM workerjobrelation WHERE job_id = :jobId;", nativeQuery = true)
    Iterable<WorkerJobRelationEntity> findAllByJobId(@Param("jobId")Long jobId);

    @Transactional
    @Query(value = "SELECT * FROM workerjobrelation WHERE worker_id = :workerId;", nativeQuery = true)
    Iterable<WorkerJobRelationEntity> findAllByWorkerId(@Param("workerId")Long workerId);

    boolean existsByJobIdAndWorkerId(Long jobId, Long workerId);
}
