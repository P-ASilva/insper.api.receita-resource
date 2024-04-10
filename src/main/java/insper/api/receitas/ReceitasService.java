package insper.api.receitas;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NonNull;

@Service
public class ReceitasService {

    @Autowired
    private ReceitasRepository receitasRepository;

    @Autowired
    private ComponenteRepository componenteRepository;

    public Receita create(Receita in) {
        return receitasRepository.save(new ReceitaModel(in)).to();
    }

    public Receita get(@NonNull String id) {
        return receitasRepository.findById(id).map(ReceitaModel::to).orElse(null);
    }

    public List<ReceitaOut> read() {
        List<ReceitaOut> receitas = new ArrayList<>();
        receitasRepository.findAll().forEach(receita -> receitas.add(ReceitasParser.to(receita.to())));
        return receitas;
    }

    public Receita update(@NonNull String id, Receita in) {
        return receitasRepository.save(new ReceitaModel(in)).to();
    }

    public ComponenteModel getQuantidadeIngrediente(@NonNull String id, @NonNull String idIngrediente) {
        return componenteRepository.findByIdReceitaAndIdIngrediente(id, idIngrediente);
    }

    public List<ComponenteModel> getIngredientes(@NonNull String id) {
        return componenteRepository.findByIdReceita(id);
    }

    public List<ComponenteModel> getReceitas(@NonNull String idIngrediente) {
        return componenteRepository.findByIdIngrediente(idIngrediente);
    }


}
