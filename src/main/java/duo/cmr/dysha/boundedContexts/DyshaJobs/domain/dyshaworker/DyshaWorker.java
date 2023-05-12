package duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaworker;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshajob.DyshaJob;
import lombok.*;

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
    private Long userId;
    private String encodedImageProfile;

    public DyshaWorker(String name, List<DyshaJob> jobs, String description, String localisation, LocalDateTime startedOn, Long userId) {
        this.name = name;
        this.jobs = jobs;
        this.description = description;
        this.localisation = localisation;
        this.startedOn = startedOn;
        this.userId = userId;
    }

    public DyshaWorker(){

    }

    public  String tableName(){
        return   "dyshaworker";
    }

    public  boolean hasNoImage(){
        return  encodedImageProfile.isEmpty();
    }
}
