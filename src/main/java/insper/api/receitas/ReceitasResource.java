package insper.api.receitas;

import java.util.Map;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class ReceitasResource implements ReceitasController {

    @Autowired
    private ReceitasService receitasService;

    @GetMapping("/receitas/info")
    public ResponseEntity<Map<String, String>> info() {
        return new ResponseEntity<Map<String, String>>(
            Map.ofEntries(
                Map.entry("microservice.name", ReceitasApplication.class.getSimpleName()),
                Map.entry("os.arch", System.getProperty("os.arch")),
                Map.entry("os.name", System.getProperty("os.name")),
                Map.entry("os.version", System.getProperty("os.version")),
                Map.entry("file.separator", System.getProperty("file.separator")),
                Map.entry("java.class.path", System.getProperty("java.class.path")),
                Map.entry("java.home", System.getProperty("java.home")),
                Map.entry("java.vendor", System.getProperty("java.vendor")),
                Map.entry("java.vendor.url", System.getProperty("java.vendor.url")),
                Map.entry("java.version", System.getProperty("java.version")),
                Map.entry("line.separator", System.getProperty("line.separator")),
                Map.entry("path.separator", System.getProperty("path.separator")),
                Map.entry("user.dir", System.getProperty("user.dir")),
                Map.entry("user.home", System.getProperty("user.home")),
                Map.entry("user.name", System.getProperty("user.name")),
                Map.entry("jar", new java.io.File(
                    ReceitasApplication.class.getProtectionDomain()
                        .getCodeSource()
                        .getLocation()
                        .getPath()
                    ).toString())
            ), HttpStatus.OK
        );
    }

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
    public ResponseEntity<ReceitaOut> update(String id, ReceitaIn in) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public ResponseEntity<ReceitaOut> get(String id) {
        final ReceitaOut receita = ReceitasParser.to(receitasService.get(id));
        //throw new UnsupportedOperationException("Unimplemented method 'get'");
        return ResponseEntity.ok(receita);
    }

    @Override
    public ResponseEntity<List<ReceitaOut>> read() {
        final List<ReceitaOut> receitas = receitasService.read();
        //throw new UnsupportedOperationException("Unimplemented method 'read'");
        return ResponseEntity.ok(receitas);
    }
}
