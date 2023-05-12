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
    private LocalDateTime startedOn;

    public WorkerJobRelation(Long jobId, Long workerId, LocalDateTime startedOn) {
        this.jobId = jobId;
        this.workerId = workerId;
        this.startedOn = startedOn;
    }
}
