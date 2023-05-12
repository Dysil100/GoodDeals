package duo.cmr.dysha.boundedContexts.DyshaJobs.domain;


import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DyshaJob {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime postedDate;
    private String employeur;
    private String location;

    public DyshaJob(String title, String description, String employeur, String location) {
        this.title = title;
        this.description = description;
        this.employeur = employeur;
        this.location = location;
        this.postedDate = LocalDateTime.now();
    }
}
