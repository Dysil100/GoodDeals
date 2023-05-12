package duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.interfaces;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaworker.DyshaWorker;

import java.util.List;

public interface DyshaWorkerRepository {
    List<DyshaWorker> findAllWorker();

    void save(DyshaWorker worker);

    DyshaWorker findById(Long id);

    DyshaWorker findByUserId(Long userId);

    boolean existByuserId(Long userId);
}
