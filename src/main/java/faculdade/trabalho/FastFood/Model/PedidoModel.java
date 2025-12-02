package faculdade.trabalho.FastFood.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pedido")
public class PedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome; // Nome do pedido
    private String formaPagamento;
    private double precoTotal;

    private String status; // "em espera", "em preparo", "concluido"

    private LocalDateTime dataHora;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ItemPedidoModel> itens = new ArrayList<>();

    public PedidoModel() {
        this.status = "em espera";
        this.dataHora = LocalDateTime.now();
    }

    public PedidoModel(String nome, String formaPagamento) {
        this.nome = nome;
        this.formaPagamento = formaPagamento;
        this.status = "em espera";
        this.dataHora = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(String formaPagamento) { this.formaPagamento = formaPagamento; }

    public double getPrecoTotal() { return precoTotal; }
    public void setPrecoTotal(double precoTotal) { this.precoTotal = precoTotal; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public List<ItemPedidoModel> getItens() { return itens; }
    public void setItens(List<ItemPedidoModel> itens) { this.itens = itens; }
}