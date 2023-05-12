package duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.photo;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table("dysha_file")
public class DyshaFileEntity {
    @Id
    private int id;
    private Long userId;
    private Long entityId;
    private String tableName;
    private String fileType;
    private byte[] file;

    public DyshaFileEntity(Long userId, Long entityId, String tableName, String fileType, byte[] file) {
        this.userId = userId;
        this.entityId = entityId;
        this.tableName = tableName;
        this.file = file;
        this.fileType = fileType;
    }

}
