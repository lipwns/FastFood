package faculdade.trabalho.FastFood.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_pedido")
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

    // Getters e setters
    public Long getId() { return id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getItens() { return itens; }
    public void setItens(String itens) { this.itens = itens; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public double getPrecoTotal() { return precoTotal; }
    public void setPrecoTotal(double precoTotal) { this.precoTotal = precoTotal; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(String formaPagamento) { this.formaPagamento = formaPagamento; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
}