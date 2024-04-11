package insper.api.receitas;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lombok.NonNull;

@Repository
public interface ComponenteRepository extends CrudRepository<ComponenteModel, String> {
    List<ComponenteModel> findAllByReceita(@NonNull String receita);  
}