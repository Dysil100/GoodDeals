package duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.workerjobrelation;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.workerjobrelation.WorkerJobRelation;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.interfaces.WorkerJobRelationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class WorkerJobRelationRepositoryImpl implements WorkerJobRelationRepository {
    private DaoWorkerJobRelationRepository daoWorkerJobRelationRepository;

    @Override
    public List<WorkerJobRelation> findAll() {
        return toWorkerJobRelationList(daoWorkerJobRelationRepository.findAll());
    }

    @Override
    public List<WorkerJobRelation> findAllByJobId(Long jobId) {
        return toWorkerJobRelationList(daoWorkerJobRelationRepository.findAllByJobId(jobId));
    }

    @Override
    public List<WorkerJobRelation> findAllByWorkerId(Long workerId) {
        return toWorkerJobRelationList(daoWorkerJobRelationRepository.findAllByWorkerId(workerId));
    }

    @Override
    public void save(WorkerJobRelation workerJobRelation) {
        if (!daoWorkerJobRelationRepository.existsByJobIdAndWorkerId(workerJobRelation.getJobId(), workerJobRelation.getWorkerId())) {
            daoWorkerJobRelationRepository.save(toWorkerJobRelationEntity(workerJobRelation));
        }
    }

    private WorkerJobRelationEntity toWorkerJobRelationEntity(WorkerJobRelation wjr) {
    return new WorkerJobRelationEntity(wjr.getJobId(), wjr.getWorkerId(), wjr.getStartedOn());
    }

    private List<WorkerJobRelation> toWorkerJobRelationList(Iterable<WorkerJobRelationEntity> all) {
        List<WorkerJobRelation> result = new ArrayList<>();
        all.forEach(e -> result.add(toWorkerJob(e)));
        return result;
    }

    private WorkerJobRelation toWorkerJob(WorkerJobRelationEntity e) {
        return new WorkerJobRelation(e.getId(), e.getJobId(), e.getWorkerId(), e.getStartedOn());
    }
}
