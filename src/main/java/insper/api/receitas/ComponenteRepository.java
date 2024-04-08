package insper.api.receitas;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponenteRepository extends CrudRepository<ComponenteModel, String> {
}