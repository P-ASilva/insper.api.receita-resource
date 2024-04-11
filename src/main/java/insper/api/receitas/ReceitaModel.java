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
@Table(name = "receita")
@EqualsAndHashCode(of = "id")
@Builder @Getter @Setter @Accessors(chain = true, fluent = true)
@NoArgsConstructor @AllArgsConstructor
public class ReceitaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_receita")
    private String id;

    @Column(name = "tx_name")
    private String name;

    @Column(name = "id_creator")
    private String creator_id;

    public ReceitaModel(Receita o) {
        this.id = o.id();
        this.name = o.name();
        this.creator_id = o.creator_id();
    }
    
    public Receita to() {
        return Receita.builder()
            .id(id)
            .name(name)
            .creator_id(creator_id)
            .build();
    }
}
