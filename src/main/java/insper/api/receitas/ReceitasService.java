package insper.api.receitas;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NonNull;

import insper.api.ingrediente.IngredienteIn;
import insper.api.ingrediente.IngredienteOut;

@Service
public class ReceitasService {

    @Autowired
    private ReceitasRepository receitasRepository;

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

    public Receita getIngredientes(@NonNull String id) {
        return receitasRepository.findById(id).map(ReceitaModel::to).orElse(null);
    }
}
