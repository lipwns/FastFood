package faculdade.trabalho.FastFood.Model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_item_ingrediente")
@JsonIgnoreProperties({"item"}) // impede loop sem quebrar o JSON
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

    public ItemIngredienteModel() {}

    public ItemIngredienteModel(ItemModel item, IngredienteModel ingrediente, int quantidade) {
        this.item = item;
        this.ingrediente = ingrediente;
        this.quantidade = quantidade;
    }

    public Long getId() { return id; }

    public ItemModel getItem() { return item; }
    public void setItem(ItemModel item) { this.item = item; }

    public IngredienteModel getIngrediente() { return ingrediente; }
    public void setIngrediente(IngredienteModel ingrediente) { this.ingrediente = ingrediente; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this. quantidade = quantidade; }
}