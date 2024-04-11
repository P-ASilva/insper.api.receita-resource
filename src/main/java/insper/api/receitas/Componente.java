package insper.api.receitas;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Builder
@Getter @Setter @Accessors(fluent = true, chain = true)
@AllArgsConstructor @NoArgsConstructor
public class Componente {
    private String id;
    private String receita;
    private String ingrediente;
    private Integer qnt;

    public ComponenteDTO dto() {
        return ComponenteDTO.builder()
            .ingrediente(ingrediente)
            .qnt(qnt)
            .build();
    }
}
