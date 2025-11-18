package faculdade.trabalho.FastFood.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_pedido")
@Data
@NoArgsConstructor
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

    @CreationTimestamp
    private LocalDateTime dataHora; // Quando o pedido foi criado
}