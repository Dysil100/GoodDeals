package duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.dyshajob;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.DyshaJob;
import duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.workerjobrelation.DaoWorkerJobRelationRepository;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.interfaces.DyshaJobRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class DyshaJobRepositoryImpl implements DyshaJobRepository {
    DaoDyshaJobRepository daoDyshaJobRepository;
    DaoWorkerJobRelationRepository daoWorkerJobRelationRepository;

    @Override
    public List<DyshaJob> findAllById(List<Long> jobIds) {
        return toDyshaJobList(daoDyshaJobRepository.findAllById(jobIds));
    }

    @Override
    public List<DyshaJob> findJobsByExprr(String query) {
        return null;
    }
    @Override
    public List<DyshaJob> restLostJobs(String query) {
        return null;
    }

    @Override
    public List<DyshaJob> findAll() {
        return toDyshaJobList(daoDyshaJobRepository.findAll());
    }

    private List<DyshaJob> toDyshaJobList(Iterable<DyshaJobEntity> all) {
        List<DyshaJob> result = new ArrayList<>();
        all.forEach(e -> result.add(toDyshaJob(e)));
        return result;
    }

    private DyshaJob toDyshaJob(DyshaJobEntity e) {
        return new DyshaJob(e.getId(), e.getTitle(), e.getDescription(), e.getPostedDate(), e.getEmployeur(), e.getLocation());
    }

}
