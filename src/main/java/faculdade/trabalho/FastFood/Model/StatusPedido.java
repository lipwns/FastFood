package faculdade.trabalho.FastFood.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusPedido {
    EM_ESPERA("em espera"),
    EM_PREPARO("em preparo"),
    PRONTO("pronto"),
    ENTREGUE("entregue");

    private final String descricao;
}
