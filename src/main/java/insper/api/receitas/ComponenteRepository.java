package insper.api.receitas;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponenteRepository extends CrudRepository<ComponenteModel, String> {
    // return all the ingredients of a recipe
    List<ComponenteModel> findByIdReceita(String idReceita);

    // return all the recipes that have a certain ingredient
    List<ComponenteModel> findByIdIngrediente(String idIngrediente);

    // return quantity of an ingredient in a recipe
    ComponenteModel findByIdReceitaAndIdIngrediente(String idReceita, String idIngrediente);
}