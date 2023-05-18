package duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaworker;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshajob.DyshaJob;
import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.workerjobrelation.WorkerJobRelation;
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
    private List<WorkerJobRelation> workerJobRelations;
    private String description;
    private String localisation;
    private LocalDateTime startedOn;
    private Long userId;
    private String encodedImageProfile;

    public DyshaWorker(String name, List<DyshaJob> jobs, List<WorkerJobRelation> workerJobRelations, String description, String localisation, LocalDateTime startedOn, Long userId) {
        this.name = name;
        this.jobs = jobs;
        this.workerJobRelations = workerJobRelations;
        this.description = description;
        this.localisation = localisation;
        this.startedOn = startedOn;
        this.userId = userId;
    }

    public DyshaWorker(){

    }

    public  String tableName(){
        return   "Profil_photo_image";
    }

    public  boolean hasNoImage(){
        return  encodedImageProfile.isEmpty();
    }
}
