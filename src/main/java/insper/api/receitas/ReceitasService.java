package insper.api.receitas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import insper.api.ingrediente.IngredienteController;
import insper.api.ingrediente.IngredienteOut;
import lombok.NonNull;

@Service
public class ReceitasService {

    @Autowired
    private ReceitasRepository receitasRepository;

    @Autowired
    private ComponenteRepository componenteRepository;

    @Autowired
    private IngredienteController ingredienteController;

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

    public List<IngredienteOut> readAllIngredientes(@NonNull String id_receita) {
        List<IngredienteOut> ingredientes = new ArrayList<>();
        componenteRepository.findAllByReceita(id_receita).forEach(
            comp-> ingredientes.add(ingredienteController.read(comp.ingrediente()).getBody())
        );
        return ingredientes;
    }
}
