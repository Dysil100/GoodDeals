package duo.cmr.dysha.boundedContexts.DyshaJobs.domain.workerjobrelation;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class WorkerJobRelation {

    private Long id;
    private Long jobId;
    private Long workerId;
    private boolean validation;
    private LocalDateTime startedOn;

    public WorkerJobRelation(Long jobId, Long workerId, boolean validation, LocalDateTime startedOn) {
        this.jobId = jobId;
        this.workerId = workerId;
        this.validation = validation;
        this.startedOn = startedOn;
    }
}
