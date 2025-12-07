package faculdade.trabalho.FastFood.Model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_item_ingrediente")
@JsonIgnoreProperties({"item"}) // impede loop sem quebrar o JSON
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemIngredienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "item_id")
    private ItemModel item;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ingrediente_id")
    private IngredienteModel ingrediente;

    private int quantidade;
}