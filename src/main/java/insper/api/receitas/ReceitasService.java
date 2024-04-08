package insper.api.receitas;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NonNull;

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

    public Receita read() {
        return null;
        // return receitasRepository.findAll().map(ReceitaModel::to).orElse(null);
    }    
}
