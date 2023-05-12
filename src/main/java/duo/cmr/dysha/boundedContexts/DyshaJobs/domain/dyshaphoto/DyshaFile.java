package duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaphoto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class DyshaFile {
    private int id;
    private Long userId;
    private Long entityId;
    private String tableName;
    private String fileType;
    private byte[] photo;

    public DyshaFile(Long userId, Long entityId, String tableName, String fileType, byte[] files) {
        this.userId = userId;
        this.entityId = entityId;
        this.tableName = tableName;
        this.photo = files;
        this.fileType = fileType;
    }


    public DyshaFile() {
    }
}
