package insper.api.receitas;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "componente")
@EqualsAndHashCode(of = "id")
@Builder @Getter @Setter @Accessors(chain = true, fluent = true)
@NoArgsConstructor @AllArgsConstructor
public class ComponenteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_componente")
    private String id;
    
    @Column(name = "id_receita")
    private String id_receita;

    @Column(name = "id_ingrediente")
    private String id_ingrediente;
    @Column(name = "qtd")
    private Integer qtd;

    public ComponenteModel(Componente o) {
        this.id = o.id();
        this.id_receita = o.id_receita();
        this.id_ingrediente = o.id_ingrediente();
        this.qtd = o.qtd();
    }
    
    public Componente to() {
        return Componente.builder()
            .id(id)
            .id_receita(id_receita)
            .id_ingrediente(id_ingrediente)
            .qtd(qtd)
            .build();
    }
}
