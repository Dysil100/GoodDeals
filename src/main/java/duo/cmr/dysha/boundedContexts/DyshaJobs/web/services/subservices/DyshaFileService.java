package duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaphoto.DyshaFile;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.interfaces.DyshaFileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DyshaFileService {

    private DyshaFileRepository dyshaFileRepository;

    public void save(DyshaFile dyshaFile) {
        dyshaFileRepository.save(dyshaFile);
    }

    public List<DyshaFile> findAllByEntityId(Long entityId) {
        return dyshaFileRepository.findAllByEntityId(entityId);
    }
}
