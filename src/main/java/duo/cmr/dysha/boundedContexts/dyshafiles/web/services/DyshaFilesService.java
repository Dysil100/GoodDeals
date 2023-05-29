package duo.cmr.dysha.boundedContexts.dyshafiles.web.services;

import duo.cmr.dysha.boundedContexts.dyshafiles.domain.DyshaFiles;
import duo.cmr.dysha.boundedContexts.dyshafiles.web.interfaces.DyshaFilesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DyshaFilesService {
    DyshaFilesRepository dyshaFilesRepository;


    public DyshaFiles findByName(String filename) {
        return dyshaFilesRepository.findByName(filename);
    }

}
