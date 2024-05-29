package insper.api.receitas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import insper.api.ingrediente.IngredienteController;
import insper.api.ingrediente.IngredienteOut;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.NonNull;

@Service
public class ReceitasService {

    @Autowired
    private ReceitasRepository receitasRepository;

    @Autowired
    private ComponenteRepository componenteRepository;

    @Autowired
    private IngredienteController ingredienteController;

    @CircuitBreaker(name = "receitasService", fallbackMethod = "fallbackCreate")
    public Receita create(Receita in, List<ComponenteDTO> componentes) {
        Receita r = receitasRepository.save(new ReceitaModel(in)).to();
        componentes.forEach(c ->
            createComponente(c, r.id())
        );
        return r;
    }
    
<<<<<<< HEAD
    public Componente createComponente(@NonNull String id_receita, ComponenteDTO in) {
        Componente c  = new Componente().ingrediente(in.ingrediente()).qnt(in.qnt()).receita(id_receita);

        return componenteRepository.save(new ComponenteModel(c)).to(); 
    }
    
=======
    @CircuitBreaker(name = "receitasService", fallbackMethod = "fallbackCreateComponente")
    public Componente createComponente(ComponenteDTO in, String id_receita) {
        Componente c  = new Componente().ingrediente(in.ingrediente()).qnt(in.qnt()).receita(id_receita);
        if (c.id() != null) {
            return componenteRepository.save(new ComponenteModel(c)).to();
        } else {
            return null;
        }  
    }

    @CircuitBreaker(name = "receitasService", fallbackMethod = "fallbackGet")
>>>>>>> e162ed2eaa1ef8703c055947b5d7c6b336061914
    public Receita get(@NonNull String id) {
        return receitasRepository.findById(id).map(ReceitaModel::to).orElse(null);
    }

    @CircuitBreaker(name = "receitasService", fallbackMethod = "fallbackGetComponentes")
    public List<ComponenteDTO> getComponentes(@NonNull String id) {
        List<ComponenteDTO> componentes = new ArrayList<>();
        componenteRepository.findAllByReceita(id).forEach(componente -> componentes.add(componente.to().dto()));
        return componentes;
    }

    @CircuitBreaker(name = "receitasService", fallbackMethod = "fallbackUpdate")
    public Receita update(@NonNull String id) {
        return receitasRepository.findById(id).get().to();
    }

    @CircuitBreaker(name = "receitasService", fallbackMethod = "fallbackRead")
    public List<ReceitaOut> read() {
        List<ReceitaOut> receitas = new ArrayList<>();
        receitasRepository.findAll().forEach(receita -> receitas.add(ReceitasParser.to(receita.to())));
        return receitas;
    }

    @CircuitBreaker(name = "receitasService", fallbackMethod = "fallbackReadAllIngredientes")
    public List<IngredienteOut> readAllIngredientes(@NonNull String id_receita) {
        List<IngredienteOut> ingredientes = new ArrayList<>();
        componenteRepository.findAllByReceita(id_receita).forEach(
            comp -> ingredientes.add(ingredienteController.read(comp.ingrediente()).getBody())
        );
        return ingredientes;
    }

    // Métodos de fallback
    public Receita fallbackCreate(Receita in, List<ComponenteDTO> componentes, Throwable t) {
        // Lógica de fallback para create
        System.out.println("Fallback create method triggered: " + t.getMessage());
        return new Receita("fallback", "Fallback receita due to error: " + t.getMessage());
    }

    public Componente fallbackCreateComponente(ComponenteDTO in, String id_receita, Throwable t) {
        // Lógica de fallback para createComponente
        System.out.println("Fallback createComponente method triggered: " + t.getMessage());
        return new Componente().ingrediente("fallback").qnt(0).receita(id_receita);
    }

    public Receita fallbackGet(@NonNull String id, Throwable t) {
        // Lógica de fallback para get
        System.out.println("Fallback get method triggered: " + t.getMessage());
        return new Receita("fallback", "Fallback receita due to error: " + t.getMessage());
    }

    public List<ComponenteDTO> fallbackGetComponentes(@NonNull String id, Throwable t) {
        // Lógica de fallback para getComponentes
        System.out.println("Fallback getComponentes method triggered: " + t.getMessage());
        List<ComponenteDTO> fallbackList = new ArrayList<>();
        fallbackList.add(new ComponenteDTO("fallback", 0));
        return fallbackList;
    }

    public Receita fallbackUpdate(@NonNull String id, Throwable t) {
        // Lógica de fallback para update
        System.out.println("Fallback update method triggered: " + t.getMessage());
        return new Receita("fallback", "Fallback receita due to error: " + t.getMessage());
    }

    public List<ReceitaOut> fallbackRead(Throwable t) {
        // Lógica de fallback para read
        System.out.println("Fallback read method triggered: " + t.getMessage());
        List<ReceitaOut> fallbackList = new ArrayList<>();
        fallbackList.add(new ReceitaOut("fallback", "Fallback receita due to error: " + t.getMessage()));
        return fallbackList;
    }

    public List<IngredienteOut> fallbackReadAllIngredientes(@NonNull String id_receita, Throwable t) {
        // Lógica de fallback para readAllIngredientes
        System.out.println("Fallback readAllIngredientes method triggered: " + t.getMessage());
        List<IngredienteOut> fallbackList = new ArrayList<>();
        fallbackList.add(new IngredienteOut("fallback", "Fallback ingrediente due to error: " + t.getMessage()));
        return fallbackList;
    }
}
