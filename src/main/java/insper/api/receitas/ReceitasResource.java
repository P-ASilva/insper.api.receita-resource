package insper.api.receitas;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ch.qos.logback.core.model.ComponentModel;

@RestController
public class ReceitasResource implements ReceitasController {

    @Autowired
    private ReceitasService receitasService;

    @Override
    public ResponseEntity<ReceitaOut> create(ReceitaIn in) {
        // parser
        Receita receita = ReceitasParser.to(in);
        // insert using service
        receita = receitasService.create(receita);
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
    public ResponseEntity<List<ReceitaOut>> read() {
        final List<ReceitaOut> receitas = receitasService.read();
        return ResponseEntity.ok(receitas);
    }

    @Override
    public ResponseEntity<ReceitaOut> update(String id, ReceitaIn in) {
        // parser
        Receita receita = ReceitasParser.to(in);
        // insert using service
        receita = receitasService.update(id, receita);
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
