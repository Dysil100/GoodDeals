package duo.cmr.dysha.boundedContexts.DyshaJobs.web.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Setter
@Getter
public class ErrorResponse {
    private HttpStatus status;
    private String message;

}

