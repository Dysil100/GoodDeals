package duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.workerjobrelation.WorkerJobRelation;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.interfaces.WorkerJobRelationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class WorkerJobRelationService {

    WorkerJobRelationRepository workerJobRelationRepository;

    public List<WorkerJobRelation> findAll(){
        return workerJobRelationRepository.findAll();
    }

    public void save(WorkerJobRelation workerJobRelation) {
        workerJobRelationRepository.save(workerJobRelation);
    }

    public WorkerJobRelation findById(Long id) {
        return workerJobRelationRepository.findById(id);
    }

    public void validateJobRelationById(Long id) {
        workerJobRelationRepository.validateJobRelationById(id);
    }

    public void deleteJobRelationById(Long id) {
        workerJobRelationRepository.deleteJobRelationById(id);
    }
}
