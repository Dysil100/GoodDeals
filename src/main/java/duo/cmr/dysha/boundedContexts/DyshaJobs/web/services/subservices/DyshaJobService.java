package duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.DyshaJob;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.interfaces.DyshaJobRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DyshaJobService {

    DyshaJobRepository dyshaJobRepository;

    public List<DyshaJob> findAllJobsByExorr(String query) {
        return dyshaJobRepository.findJobsByExprr(query);
    }

    public List<DyshaJob> restLostJobs(String query) {
        return dyshaJobRepository.restLostJobs(query);
    }

    public List<DyshaJob> findAll() {
        return dyshaJobRepository.findAll();
    }
}
