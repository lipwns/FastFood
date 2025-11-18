package faculdade.trabalho.FastFood.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "item_ingrediente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemIngredienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemModel item;

    @ManyToOne
    @JoinColumn(name = "ingrediente_id")
    private IngredienteModel ingrediente;

    private int quantidade; // quantidade de ingrediente usada neste item
}