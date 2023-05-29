package duo.cmr.dysha.boundedContexts.dyshafiles.persistance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DaoDyshaFilesRepository extends CrudRepository<DyshaFilesEntity, Long> {

    DyshaFilesEntity findByName(String fileName);
}