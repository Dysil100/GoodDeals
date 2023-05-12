package duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.photo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DaoPhotoRepository extends CrudRepository<DyshaFileEntity, Long> {
    Iterable<DyshaFileEntity> findAllByTableNameAndUserIdAndEntityId(String tableName, Long userId, Long entityId);

    Iterable<DyshaFileEntity> findAllByTableNameAndEntityId(String tableName, Long entityId);
}
