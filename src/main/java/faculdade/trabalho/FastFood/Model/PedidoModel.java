package faculdade.trabalho.FastFood.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_pedido")
@Data
@AllArgsConstructor
public class PedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome; // Ex: "Pedido #1"
    private String itens; // Ex: "Hamburguer, Refrigerante"
    private int quantidade; // Quantidade total de itens
    private double precoTotal;
    private String status; // Ex: "Em preparo", "Pronto", "Entregue"
    private String formaPagamento; // Ex: "Dinheiro", "Cartão"

    private LocalDateTime dataHora; // Quando o pedido foi criado

    public PedidoModel() {
        this.dataHora = LocalDateTime.now();
    }

    public PedidoModel(String nome, String itens, int quantidade, double precoTotal, String status, String formaPagamento) {
        this.nome = nome;
        this.itens = itens;
        this.quantidade = quantidade;
        this.precoTotal = precoTotal;
        this.status = status;
        this.formaPagamento = formaPagamento;
        this.dataHora = LocalDateTime.now();
    }
}