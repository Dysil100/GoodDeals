package duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.dyshaworker;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.DyshaJob;
import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.DyshaWorker;
import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.WorkerJobRelation;
import duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.dyshajob.DyshaJobRepositoryImpl;
import duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.workerjobrelation.WorkerJobRelationRepositoryImpl;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.interfaces.DyshaWorkerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class DyshaWorkerRepositoryImpl implements DyshaWorkerRepository {

    DaoDyshaWorkerRepository daoDyshaWorkerRepository;
    WorkerJobRelationRepositoryImpl workerJobRelationRepository;
    DyshaJobRepositoryImpl dyshaJobRepository;
    @Override
    public List<DyshaWorker> findAllWorker() {
        return toWorkerList(daoDyshaWorkerRepository.findAll());
    }

    private List<DyshaWorker> toWorkerList(Iterable<DyshaWorkerEntity> all) {
        List<DyshaWorker> result = new ArrayList<>();
        all.forEach(e -> result.add(toWorker(e)));
        return result;
    }

    private DyshaWorker toWorker(DyshaWorkerEntity e) {
        List<DyshaJob> allByWorkerId = dyshaJobRepository.findAllById(workerJobRelationRepository.findAllByWorkerId(e.getId()).stream().map(WorkerJobRelation::getJobId).toList());
        return new DyshaWorker(e.getId(), e.getName(), allByWorkerId, e.getDescription(), e.getLocation(), e.getStartedOn());
    }
}
