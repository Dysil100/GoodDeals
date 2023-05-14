package duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.interfaces;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.workerjobrelation.WorkerJobRelation;

import java.util.List;

public interface WorkerJobRelationRepository {

    List<WorkerJobRelation> findAll();

    List<WorkerJobRelation> findAllByJobId(Long jobId);

    List<WorkerJobRelation> findAllByWorkerId(Long workerId);

    void save(WorkerJobRelation workerJobRelation);

    WorkerJobRelation findById(Long id);

    void validateJobRelationById(Long id);

    void deleteJobRelationById(Long id);
}
