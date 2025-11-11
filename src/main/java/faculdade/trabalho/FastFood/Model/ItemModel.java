package faculdade.trabalho.FastFood.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "itens")
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

    public ItemModel() {}

    public ItemModel(String nome, String descricao, double preco, List<IngredienteModel> ingredientes) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.ingredientes = ingredientes;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public List<IngredienteModel> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredienteModel> ingredientes) {
        this.ingredientes = ingredientes;
    }
}