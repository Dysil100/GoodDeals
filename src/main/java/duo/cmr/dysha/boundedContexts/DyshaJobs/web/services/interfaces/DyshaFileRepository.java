package duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.interfaces;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaphoto.DyshaFile;

import java.util.List;

public interface DyshaFileRepository {
    void save(DyshaFile dyshaFile);

    List<DyshaFile> findAllByTableNameAndUserIdAndEntityIdAndFileType(String dyshaworker, Long userId, Long id, String fileType);

    String findLastByTableNameAndUserIdAndEntityIdAndFileType(String dyshaworker, Long userId, Long entityId, String fileType);

    String findLastByTableNameAndEntityIdAndFileType(String tableName, Long entityId, String fileType);

    List<DyshaFile> findAllByTableNameAndEntityIdAndFileType(String tableName, Long entityId, String fileType);

    List<DyshaFile> findAllByUserId(Long userId);

    DyshaFile findFieById(Long fileId);

    void deleteById(Long fileId);
}
