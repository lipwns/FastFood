package faculdade.trabalho.FastFood.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pedido")
@Getter
@Setter
@AllArgsConstructor
public class PedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome; // Nome do pedido
    private String formaPagamento;
    private double precoTotal;

    private String status; // "em espera", "em preparo", "concluido"

    @CreationTimestamp
    private LocalDateTime dataHora;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ItemPedidoModel> itens = new ArrayList<>();

    public PedidoModel() {
        this.status = "em espera";
    }

    public PedidoModel(String nome, String formaPagamento) {
        this.nome = nome;
        this.formaPagamento = formaPagamento;
        this.status = "em espera";
    }
}