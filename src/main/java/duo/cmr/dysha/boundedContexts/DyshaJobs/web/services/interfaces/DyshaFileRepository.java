package duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.interfaces;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaphoto.DyshaFile;

import java.util.List;

public interface DyshaPhotoRepository {
    void save(DyshaFile dyshaFile);

    List<DyshaFile> findAllByTableNameAndUserIdAndEntityId(String dyshaworker, Long userId, Long id);

    String findLastByTableNameAndUserIdAndEntityId(String dyshaworker, Long userId, Long entityId);

    String findLastByTableNameAndEntityId(String tableName, Long entityId);

    List<DyshaFile> findAllByTableNameAndEntityId(String tableName, Long entityId);
}
