package insper.api.receitas;

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
@Table(name="componente")
@EqualsAndHashCode(of = "id")
@Builder @Getter @Setter @Accessors(chain = true, fluent = true)
@NoArgsConstructor @AllArgsConstructor
public class ComponenteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_componente")
    private String id;
    
    @Column(name = "Id_receita")
    private String receita;

    @Column(name = "id_ingrediente")
    private String ingrediente;
    @Column(name = "qtd")
    private Integer qtd;

    public ComponenteModel(Componente o) {
        this.id = o.id();
        this.receita = o.receita();
        this.ingrediente = o.ingrediente();
        this.qtd = o.qtd();
    }
    
    public Componente to() {
        return Componente.builder()
            .id(id)
            .receita(receita)
            .ingrediente(ingrediente)
            .qtd(qtd)
            .build();
    }
}
