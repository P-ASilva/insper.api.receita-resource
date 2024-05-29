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

    public Receita create(Receita in, List<ComponenteDTO> componentes) {
        Receita r = receitasRepository.save(new ReceitaModel(in)).to();
        componentes.forEach(c ->
            createComponente(c, r.id())
        );
        return r;
    }
    
    public Componente createComponente(@NonNull String id_receita, ComponenteDTO in) {
        Componente c  = new Componente().ingrediente(in.ingrediente()).qnt(in.qnt()).receita(id_receita);

        return componenteRepository.save(new ComponenteModel(c)).to(); 
    }
    
    public Receita get(@NonNull String id) {
        return receitasRepository.findById(id).map(ReceitaModel::to).orElse(null);
    }

    public List<ComponenteDTO> getComponentes(@NonNull String id) {
        List<ComponenteDTO> componentes = new ArrayList<>();
        // System.err.println(componenteRepository.findAllByReceita(id));
        componenteRepository.findAllByReceita(id).forEach(componente -> componentes.add(componente.to().dto()));

        return componentes;
    }

    public Receita update(@NonNull String id) {
        return receitasRepository.findById(id).get().to();
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
