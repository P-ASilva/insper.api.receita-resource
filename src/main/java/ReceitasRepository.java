package insper.api.receitas;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitasRepository extends CrudRepository<ReceitaModel, String> {
}