package duo.cmr.dysha.boundedContexts.DyshaJobs.domain.globaluser;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshajob.DyshaJob;
import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaworker.DyshaWorker;
import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.workerjobrelation.WorkerJobRelation;
import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class GlobalAppUser {
    private AppUser user;
    private DyshaWorker worker;

    public boolean dyshaUserExist(){
        return worker != null;
    }

    public boolean alreadyPostlutlateFor(Long jobId){
        return worker.getJobs().stream().map(DyshaJob::getId).toList().contains(jobId);
    }

    public boolean hasValidationForJob(Long jobId){
        return worker.getWorkerJobRelations().stream().filter(w -> Objects.equals(w.getJobId(), jobId)).toList().get(0).isValidation();
    }
}
