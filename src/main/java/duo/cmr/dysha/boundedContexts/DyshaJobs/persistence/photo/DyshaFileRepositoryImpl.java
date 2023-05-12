package duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.photo;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaphoto.DyshaFile;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.interfaces.DyshaPhotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@AllArgsConstructor
@Repository
public class DyshaPhotoRepositoryImpl implements DyshaPhotoRepository {
    DaoPhotoRepository daoPhotoRepository;
    @Override
    public void save(DyshaFile dyshaFile) {
     daoPhotoRepository.save(toDyshaPhotoEntity(dyshaFile));
    }

    @Override
    public List<DyshaFile> findAllByTableNameAndUserIdAndEntityId(String dyshaworker, Long userId, Long id) {
        return toDyshaPhotoList(daoPhotoRepository.findAllByTableNameAndUserIdAndEntityId(dyshaworker, userId, id));
    }

    @Override
    public String findLastByTableNameAndUserIdAndEntityId(String tableName, Long userId, Long entityId) {
        List<DyshaFile> dyshaFiles = findAllByTableNameAndUserIdAndEntityId(tableName, userId, entityId);
        return dyshaFiles.isEmpty() ? "" : Base64.getEncoder().encodeToString(dyshaFiles.get(dyshaFiles.size() - 1).getPhoto());
    }

    @Override
    public String findLastByTableNameAndEntityId(String tableName, Long entityId) {
        List<DyshaFile> dyshaFiles = findAllByTableNameAndEntityId(tableName, entityId);
        return dyshaFiles.isEmpty() ? "" : Base64.getEncoder().encodeToString(dyshaFiles.get(dyshaFiles.size() - 1).getPhoto());
    }

    @Override
    public List<DyshaFile> findAllByTableNameAndEntityId(String tableName, Long entityId) {
        return toDyshaPhotoList(daoPhotoRepository.findAllByTableNameAndEntityId(tableName, entityId));
    }

    private List<DyshaFile> toDyshaPhotoList(Iterable<DyshaFileEntity> allBys) {
        List<DyshaFile> result = new ArrayList<>();
        allBys.forEach(e -> result.add(toDyshaPhoto(e)));
        return result;
    }

    private DyshaFile toDyshaPhoto(DyshaFileEntity e) {
        return new DyshaFile(e.getId(), e.getUserId(), e.getEntityId(), e.getTableName(), e.getFileType(), e.getFile());
    }

    private DyshaFileEntity toDyshaPhotoEntity(DyshaFile dyshaFile) {
        return new DyshaFileEntity(dyshaFile.getUserId(), dyshaFile.getEntityId(), dyshaFile.getTableName(), dyshaFile.getFileType(), dyshaFile.getPhoto());
    }
}
