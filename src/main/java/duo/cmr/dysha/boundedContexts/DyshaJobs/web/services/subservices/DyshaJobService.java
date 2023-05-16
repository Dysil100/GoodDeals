package duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshajob.DyshaJob;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.interfaces.DyshaJobRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DyshaJobService {

    DyshaJobRepository dyshaJobRepository;

    public List<DyshaJob> findAllJobsByExorr(String query)  {
        return dyshaJobRepository.findJobsByExprr(query);
    }

    public List<DyshaJob> restListJobs(String query) {
        return dyshaJobRepository.restListJobs(query);
    }

    public List<DyshaJob> findAll() {
        return dyshaJobRepository.findAll();
    }

    public void save(DyshaJob dyshaJob) {
         dyshaJobRepository.save(dyshaJob);
    }

    public DyshaJob getJobById(Long id) {
        return dyshaJobRepository.findByID(id);
    }
}
