package duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.dyshaworker;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table("dyshaworker")
public class DyshaWorkerEntity {
    @Id
    private Long id;
    private String name;
    private String description;
    private String location;
    private LocalDateTime startedOn;
    private Long userId;

    public DyshaWorkerEntity(String name, String description, String location, LocalDateTime startedOn, Long userId) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.startedOn = startedOn;
        this.userId = userId;
    }
}
