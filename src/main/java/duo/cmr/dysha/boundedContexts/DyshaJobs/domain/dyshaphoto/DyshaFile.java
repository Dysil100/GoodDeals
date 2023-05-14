package duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaphoto;

import lombok.*;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

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
    private byte[] file;

    public DyshaFile(Long userId, Long entityId, String tableName, String fileType, byte[] file) {
        this.userId = userId;
        this.entityId = entityId;
        this.tableName = tableName;
        this.file = file;
        this.fileType = fileType;
    }

    public DyshaFile() {
    }

    public HttpHeaders buildHeadersFor(String userName) {
        MediaType mediaType = switch (fileType) {
            case "image" -> MediaType.IMAGE_JPEG;
            case "pdf" -> MediaType.APPLICATION_PDF;
            default -> MediaType.APPLICATION_OCTET_STREAM;
        };
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(getFilenameFor(userName)).build());
        headers.setContentLength(file.length);

        return headers;
    }

    private String getFilenameFor(String userName) {
        String extention = ".pdf";
        if (fileType.equalsIgnoreCase("image")) extention = ".png";
        return tableName + '_' + "of_" + userName + extention;
    }
}
