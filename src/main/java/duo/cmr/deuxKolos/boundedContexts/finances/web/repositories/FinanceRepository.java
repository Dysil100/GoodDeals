package duo.cmr.deuxKolos.boundedContexts.finances.web.repositories;

import duo.cmr.deuxKolos.boundedContexts.finances.forms.Finance;

import java.util.List;

public interface FinanceRepository {
    List<Finance> findAll();

    void save(Finance finance);

    void deleteById(Long id);

    void deleteAll();

    List<Finance> alleByProjectName(String projectName);

    void update(Finance finance);

    Finance findById(Long id);
}
