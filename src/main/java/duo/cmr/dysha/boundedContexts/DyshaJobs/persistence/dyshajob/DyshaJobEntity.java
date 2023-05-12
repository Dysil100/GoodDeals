package duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.dyshajob;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table("dyshajob")
public class DyshaJobEntity {

    @Id
    private Long id;
    private String title;
    private String description;
    private LocalDateTime postedDate;
    private String employeur;
    private String location;
    public Long userId;

    public DyshaJobEntity(String title, String description, LocalDateTime postedDate, String employeur, String location, Long userId) {
        this.title = title;
        this.description = description;
        this.postedDate = postedDate;
        this.employeur = employeur;
        this.location = location;
        this.userId = userId;
    }
}
