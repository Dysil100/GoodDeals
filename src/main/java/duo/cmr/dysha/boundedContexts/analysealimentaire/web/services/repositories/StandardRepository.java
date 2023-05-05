package duo.cmr.dysha.boundedContexts.analysealimentaire.web.services.repositories;


import duo.cmr.dysha.boundedContexts.analysealimentaire.domain.Standard;

import java.util.List;

public interface



StandardRepository {
    Standard findByDescription(String name);
    Standard findById(Long id);
    void save(Standard standard);
    void delete(Standard standard);
    List<Standard> alle();
}
