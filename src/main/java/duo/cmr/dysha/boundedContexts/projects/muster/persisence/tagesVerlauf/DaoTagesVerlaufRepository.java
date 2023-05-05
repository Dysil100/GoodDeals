package duo.cmr.dysha.boundedContexts.projects.muster.persisence.tagesVerlauf;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DaoTagesVerlaufRepository extends CrudRepository<TagesVerlaufEntity, Long> {

    List<TagesVerlaufEntity> findAllByProjectName(String projectName);
}