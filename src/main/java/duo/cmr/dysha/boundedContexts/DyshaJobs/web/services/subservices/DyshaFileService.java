package duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.DyshaJobValidations;
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

    public boolean cVExistByEntityId(Long entityId) {
        return !dyshaFileRepository.findLastByTableNameAndEntityIdAndFileType("CV_document", entityId, "pdf").isBlank();
    }

    public DyshaFile findFileById(Long fileId) {
        return dyshaFileRepository.findFieById(fileId);
    }

    public void deleteById(Long fileId) {
        dyshaFileRepository.deleteById(fileId);
    }

    public DyshaJobValidations getValidationsFor(Long entityId) {
        return dyshaFileRepository.getValidationFor(entityId);
    }

    public List<DyshaFile> findAllByEntityId(Long entityId) {
        return dyshaFileRepository.findAllByEntityId(entityId);
    }
}
