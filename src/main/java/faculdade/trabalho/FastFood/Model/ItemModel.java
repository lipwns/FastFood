package faculdade.trabalho.FastFood.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "itens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private double preco;

    @ManyToMany
    @JoinTable(
            name = "item_ingrediente",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    private List<IngredienteModel> ingredientes;
}