package duo.cmr.deuxKolos.boundedContexts.avis.web.service;

import duo.cmr.deuxKolos.boundedContexts.avis.forms.Avis;
import duo.cmr.deuxKolos.boundedContexts.avis.web.repositories.AvisRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AvisService {


    private AvisRepository avisRepository;

    public void save(Avis avis) {
        avisRepository.save(avis);
    }

    public void deleteById(Long id) {
        avisRepository.deleteById(id);
    }

    public List<Avis> alle() {
        return avisRepository.findAll();
    }
}
