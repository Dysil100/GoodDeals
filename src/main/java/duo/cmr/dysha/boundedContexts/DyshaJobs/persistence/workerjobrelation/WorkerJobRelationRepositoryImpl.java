package duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.workerjobrelation;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.workerjobrelation.WorkerJobRelation;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.interfaces.WorkerJobRelationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public WorkerJobRelation findById(Long id) {
        return toWorkerJobRelation(Objects.requireNonNull(daoWorkerJobRelationRepository.findById(id).orElse(null)));
    }

    @Override
    public void validateJobRelationById(Long id) {
        WorkerJobRelationEntity e = daoWorkerJobRelationRepository.findById(id).orElse(null);
        daoWorkerJobRelationRepository.deleteById(id);
        assert e != null;
        daoWorkerJobRelationRepository.save(new WorkerJobRelationEntity(e.getJobId(), e.getWorkerId(), true, LocalDateTime.now()));
    }

    @Override
    public void deleteJobRelationById(Long id) {
        daoWorkerJobRelationRepository.deleteById(id);
    }

    private WorkerJobRelationEntity toWorkerJobRelationEntity(WorkerJobRelation wjr) {
    return new WorkerJobRelationEntity(wjr.getJobId(), wjr.getWorkerId(), wjr.isValidation(), wjr.getStartedOn());
    }

    private List<WorkerJobRelation> toWorkerJobRelationList(Iterable<WorkerJobRelationEntity> all) {
        List<WorkerJobRelation> result = new ArrayList<>();
        all.forEach(e -> result.add(toWorkerJobRelation(e)));
        return result;
    }

    private WorkerJobRelation toWorkerJobRelation(WorkerJobRelationEntity e) {
        return (e == null) ? null : new WorkerJobRelation(e.getId(), e.getJobId(), e.getWorkerId(), e.isValidation(), e.getStartedOn());
    }
}
