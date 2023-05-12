package duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshajob;


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
    private Long userId;
    private  String encodedImage;

    public DyshaJob(String title, String description, String employeur, String location, Long userId, String encodedImage) {
        this.title = title;
        this.description = description;
        this.employeur = employeur;
        this.location = location;
        this.postedDate = LocalDateTime.now();
        this.userId = userId;
        this.encodedImage = encodedImage;
    }

    public DyshaJob(){

    }

    public  String tableName(){
        return   "dyshajob";
    }

    public boolean hasNoImage(){
        return encodedImage.isEmpty();
    }
}
