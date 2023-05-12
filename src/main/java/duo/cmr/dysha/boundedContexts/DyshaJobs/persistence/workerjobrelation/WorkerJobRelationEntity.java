package duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.workerjobrelation;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Table("workerjobrelation")
public class WorkerJobRelationEntity {

    @Id
    private Long id;
    private Long jobId;
    private Long workerId;
    private LocalDateTime startedOn;

    public WorkerJobRelationEntity(Long jobId, Long workerId, LocalDateTime startedOn) {
        this.jobId = jobId;
        this.workerId = workerId;
        this.startedOn = startedOn;
    }

    public WorkerJobRelationEntity() {
        // Constructeur par défaut
    }
}
