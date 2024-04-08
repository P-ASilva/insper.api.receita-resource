package insper.api.receitas;

public class ReceitasParser {

    public static Receita to(ReceitaIn in) {
        return Receita.builder()
            .name(in.name())
            .creator_id(in.creator_id())
            .build();
    }

    public static ReceitaOut to(Receita receita) {
        return ReceitaOut.builder()
            .id(receita.id())
            .name(receita.name())
            .build();
    }
    
}
