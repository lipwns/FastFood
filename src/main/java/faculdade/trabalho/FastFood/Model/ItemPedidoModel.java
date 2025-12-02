package faculdade.trabalho.FastFood.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_item_pedido")
public class ItemPedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantidade;

    private double precoUnitario;

    private double subtotal;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PedidoModel pedido;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemModel item;

    public ItemPedidoModel() {}

    public ItemPedidoModel(ItemModel item, int quantidade) {
        this.item = item;
        this.quantidade = quantidade;
        this.precoUnitario = item.getPreco();
        this.subtotal = this.precoUnitario * quantidade;
    }

    public Long getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        atualizarSubtotal();
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
        atualizarSubtotal();
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    private void atualizarSubtotal() {
        this.subtotal = this.precoUnitario * this.quantidade;
    }

    public PedidoModel getPedido() {
        return pedido;
    }

    public void setPedido(PedidoModel pedido) {
        this.pedido = pedido;
    }

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
        this.precoUnitario = item.getPreco();
        atualizarSubtotal();
    }
}