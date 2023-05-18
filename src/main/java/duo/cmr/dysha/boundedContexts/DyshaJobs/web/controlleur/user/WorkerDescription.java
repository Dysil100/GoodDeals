package duo.cmr.dysha.boundedContexts.DyshaJobs.web.controlleur.user;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaworker.DyshaWorker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class WorkerDescription extends DyshaWorker {
    private String description;
    private String localisation;
}
