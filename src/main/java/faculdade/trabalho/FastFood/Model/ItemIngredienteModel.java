package faculdade.trabalho.FastFood.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "item_ingrediente")
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

    public ItemIngredienteModel() {}

    public ItemIngredienteModel(ItemModel item, IngredienteModel ingrediente, int quantidade) {
        this.item = item;
        this.ingrediente = ingrediente;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
    }

    public IngredienteModel getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(IngredienteModel ingrediente) {
        this.ingrediente = ingrediente;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}