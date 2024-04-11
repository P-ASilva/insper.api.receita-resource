package insper.api.receitas;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class ReceitasResource implements ReceitasController {

    @Autowired
    private ReceitasService receitasService;

    @Override
    public ResponseEntity<ReceitaOut> create(@RequestBody ReceitaRequest r) {
        System.err.println(r);
        // parser
        Receita receita = ReceitasParser.to(r.in());
        // insert using service
        receita = receitasService.create(receita, r.componentes());
        // return
        return ResponseEntity.created(
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(receita.id())
                .toUri())
            .body(ReceitasParser.to(receita));
    }

    @Override
    public ResponseEntity<ReceitaOut> get(String id) {
        final ReceitaOut receita = ReceitasParser.to(receitasService.get(id));
        return ResponseEntity.ok(receita);
    }

    @Override
    public ResponseEntity<ReceitaOutContent> getComponentes(String id) {
        final List<ComponenteDTO> componentes = receitasService.getComponentes(id);
        final ReceitaOut receita = ReceitasParser.to(receitasService.get(id));
        return  ResponseEntity.ok(new ReceitaOutContent(receita, componentes));
    }

    @Override
    public ResponseEntity<List<ReceitaOut>> read() {
        final List<ReceitaOut> receitas = receitasService.read();
        //throw new UnsupportedOperationException("Unimplemented method 'read'");
        return ResponseEntity.ok(receitas);
    }

    @Override
    public ResponseEntity<ReceitaOut> update(String id) {
        // insert using service
        Receita receita = receitasService.update(id);
        // return
        return ResponseEntity.created(
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(receita.id())
                .toUri())
            .body(ReceitasParser.to(receita));
    }

}
