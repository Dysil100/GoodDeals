package duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaworker.DyshaWorker;
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

    public void save(DyshaWorker worker) {
        dyshaWorkerRepository.save(worker);
    }

    public DyshaWorker findById(Long id) {
        return dyshaWorkerRepository.findById(id);
    }

    public DyshaWorker findByUserId(Long userId) {
        return dyshaWorkerRepository.findByUserId(userId);
    }

    public boolean existByUserId(Long userId) {
        return dyshaWorkerRepository.existByuserId(userId);
    }
}
