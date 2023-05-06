package duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.interfaces;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.DyshaJob;

import java.util.List;

public interface DyshaJobRepository {
    List<DyshaJob> findJobsByExprr(String query);

    List<DyshaJob> findAll();

    List<DyshaJob> restLostJobs(String query);

    List<DyshaJob> findAllById(List<Long> jobIds);
}
