package duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.interfaces;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.DyshaWorker;

import java.util.List;

public interface DyshaWorkerRepository {
    List<DyshaWorker> findAllWorker();
}
