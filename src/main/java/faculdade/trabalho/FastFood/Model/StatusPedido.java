package faculdade.trabalho.FastFood.Model;

public enum StatusPedido {
    EM_ESPERA("em espera"),
    EM_PREPARO("em preparo"),
    PRONTO("pronto"),
    ENTREGUE("entregue");

    private final String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
