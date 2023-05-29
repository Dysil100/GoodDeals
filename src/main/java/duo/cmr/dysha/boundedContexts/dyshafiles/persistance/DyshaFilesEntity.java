package duo.cmr.dysha.boundedContexts.dyshafiles.persistance;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table("dysha_files")
public class DyshaFilesEntity {

    @Id
    private Long id;
    private String name;
    private byte[] data;


    public DyshaFilesEntity(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }
}
