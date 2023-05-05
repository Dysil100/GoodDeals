package duo.cmr.dysha.boundedContexts.analysealimentaire.web.services.repositories;

import duo.cmr.dysha.boundedContexts.analysealimentaire.domain.ingredients.Ingredient;

import java.util.List;

public interface IngredientRepository {
     Ingredient findByName(String name);
     Ingredient findById(Long id);
     void save(Ingredient ingredient);
     void delete(Ingredient ingredient);
    List<Ingredient> findAll();
}
