package duo.cmr.dysha.boundedContexts.DyshaJobs.domain;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DyshaWorker {

    private Long id;
    private String name;
    private List<DyshaJob> jobs;
    private String description;
    private String localisation;
    private LocalDateTime startedOn;

    public DyshaWorker(String name, List<DyshaJob> jobs, String description, String localisation, LocalDateTime startedOn) {
        this.name = name;
        this.jobs = jobs;
        this.description = description;
        this.localisation = localisation;
        this.startedOn = startedOn;
    }
}
