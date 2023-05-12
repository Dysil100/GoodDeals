package duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.photo;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table("photos")
public class DyshaPhotoEntity {
    @Id
    private int id;
    private Long userId;
    private Long entityId;
    private String tableName;
    private byte[] photo;

    public DyshaPhotoEntity(Long userId, Long entityId, String tableName, byte[] photo) {
        this.userId = userId;
        this.entityId = entityId;
        this.tableName = tableName;
        this.photo = photo;
    }
}
