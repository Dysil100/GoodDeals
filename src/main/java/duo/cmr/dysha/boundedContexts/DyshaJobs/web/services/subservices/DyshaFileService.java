package duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaphoto.DyshaFile;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.interfaces.DyshaPhotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PhotoService {

    private DyshaPhotoRepository dyshaPhotoRepository;

    public void save(DyshaFile dyshaFile) {
        dyshaPhotoRepository.save(dyshaFile);
    }
}
