package duo.cmr.deuxKolos.boundedContexts.avis.web.repositories;

import duo.cmr.deuxKolos.boundedContexts.avis.forms.Avis;

import java.util.List;

public interface AvisRepository {

    void save(Avis avis);

    void deleteById(Long id);

    List<Avis> findAll();
}
