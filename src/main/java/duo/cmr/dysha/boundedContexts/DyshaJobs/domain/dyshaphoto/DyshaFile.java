package duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaphoto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class DyshaPhoto {
    private int id;
    private Long userId;
    private Long entityId;
    private String tableName;
    private byte[] photo;

    public DyshaPhoto(Long userId, Long entityId, String tableName, byte[] photo) {
        this.userId = userId;
        this.entityId = entityId;
        this.tableName = tableName;
        this.photo = photo;
    }

    public DyshaPhoto(Long userId, Long entityId, String tableName) {
        this.userId = userId;
        this.entityId = entityId;
        this.tableName = tableName;
    }

    public DyshaPhoto() {
    }
}
