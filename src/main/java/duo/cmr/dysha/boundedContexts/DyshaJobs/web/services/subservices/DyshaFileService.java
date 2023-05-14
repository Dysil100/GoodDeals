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

    public List<DyshaFile> findAllByUserId(Long entityId) {
        return dyshaFileRepository.findAllByUserId(entityId);
    }

    public boolean cVExistByUserId(Long userId) {
        return !dyshaFileRepository.findLastByTableNameAndEntityIdAndFileType("CV_document", userId, "pdf").isBlank();
    }

    public DyshaFile findFileById(Long fileId) {
        return dyshaFileRepository.findFieById(fileId);
    }

    public void deleteById(Long fileId) {
        dyshaFileRepository.deleteById(fileId);
    }
}
