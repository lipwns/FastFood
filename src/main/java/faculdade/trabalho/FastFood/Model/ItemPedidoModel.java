package faculdade.trabalho.FastFood.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_item_pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantidade;

    private double precoUnitario;

    private double subtotal;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @JsonBackReference
    private PedidoModel pedido;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemModel item;

    public ItemPedidoModel(ItemModel item, int quantidade) {
        this.item = item;
        this.quantidade = quantidade;
        this.precoUnitario = item.getPreco();
        this.subtotal = this.precoUnitario * quantidade;
    }

    private void atualizarSubtotal() {
        this.subtotal = this.precoUnitario * this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        atualizarSubtotal();
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
        atualizarSubtotal();
    }

    public void setItem(ItemModel item) {
        this.item = item;
        this.precoUnitario = item.getPreco();
        atualizarSubtotal();
    }
}