package duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.dyshafile;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaphoto.DyshaFile;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.interfaces.DyshaFileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Repository
public class DyshaFileRepositoryImpl implements DyshaFileRepository {
    DaoDyshaFileRepository daoDyshaFileRepository;
    @Override
    public void save(DyshaFile dyshaFile) {
     daoDyshaFileRepository.save(toDyshaFileEntity(dyshaFile));
    }

    @Override
    public List<DyshaFile> findAllByTableNameAndUserIdAndEntityIdAndFileType(String dyshaworker, Long userId, Long id, String fileType) {
        return toDyshaFileList(daoDyshaFileRepository.findAllByTableNameAndUserIdAndEntityIdAndFileType(dyshaworker, userId, id, fileType));
    }

    @Override
    public String findLastByTableNameAndUserIdAndEntityIdAndFileType(String tableName, Long userId, Long entityId, String fileType) {
        List<DyshaFile> dyshaFiles = findAllByTableNameAndUserIdAndEntityIdAndFileType(tableName, userId, entityId, fileType);
        return dyshaFiles.isEmpty() ? "" : Base64.getEncoder().encodeToString(dyshaFiles.get(dyshaFiles.size() - 1).getFile());
    }

    @Override
    public String findLastByTableNameAndEntityIdAndFileType(String tableName, Long entityId, String fileType) {
        List<DyshaFile> dyshaFiles = findAllByTableNameAndEntityIdAndFileType(tableName, entityId, fileType);
        return dyshaFiles.isEmpty() ? "" : Base64.getEncoder().encodeToString(dyshaFiles.get(dyshaFiles.size() - 1).getFile());
    }

    @Override
    public List<DyshaFile> findAllByTableNameAndEntityIdAndFileType(String tableName, Long entityId, String fileType) {
        return toDyshaFileList(daoDyshaFileRepository.findAllByTableNameAndEntityIdAndFileType(tableName, entityId, fileType));
    }

    @Override
    public List<DyshaFile> findAllByUserId(Long userId) {
        return toDyshaFileList(daoDyshaFileRepository.findAllByUserId(userId));
    }

    @Override
    public DyshaFile findFieById(Long fileId) {
        return toDyshaFile(Objects.requireNonNull(daoDyshaFileRepository.findById(fileId).orElse(null)));
    }

    @Override
    public void deleteById(Long fileId) {
        daoDyshaFileRepository.deleteById(fileId);
    }

    private List<DyshaFile> toDyshaFileList(Iterable<DyshaFileEntity> allBys) {
        List<DyshaFile> result = new ArrayList<>();
        allBys.forEach(e -> result.add(toDyshaFile(e)));
        return result;
    }

    private DyshaFile toDyshaFile(DyshaFileEntity e) {
        return (e == null)? null : new DyshaFile(e.getId(), e.getUserId(), e.getEntityId(), e.getTableName(), e.getFileType(), e.getFile());
    }

    private DyshaFileEntity toDyshaFileEntity(DyshaFile dyshaFile) {
        return new DyshaFileEntity(dyshaFile.getUserId(), dyshaFile.getEntityId(), dyshaFile.getTableName(), dyshaFile.getFileType(), dyshaFile.getFile());
    }
}
