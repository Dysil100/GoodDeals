package duo.cmr.dysha.boundedContexts.DyshaJobs.domain;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DyshaJobValidations {

    private boolean pustulate;
    private boolean loadedDocuments;
    private boolean loadedCurriculumVitae;
    private boolean loadedLastDiplome;
    private boolean loadedIdCard;

    public DyshaJobValidations() {

    }
}
