package duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.DyshaWorker;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.interfaces.DyshaWorkerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DyshaWorkerService {
    DyshaWorkerRepository dyshaWorkerRepository;
    public List<DyshaWorker> finAll() {
        return dyshaWorkerRepository.findAllWorker();
    }
}
