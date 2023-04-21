package duo.cmr.deuxKolos.boundedContexts.analysealimentaire.web.services.repositories;


import duo.cmr.deuxKolos.boundedContexts.analysealimentaire.domain.Standard;

import java.util.List;

public interface



StandardRepository {
    Standard findByDescription(String name);
    Standard findById(Long id);
    void save(Standard standard);
    void delete(Standard standard);
    List<Standard> alle();
}
