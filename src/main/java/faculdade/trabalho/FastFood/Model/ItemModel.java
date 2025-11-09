package faculdade.trabalho.FastFood.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_item")
public class ItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private double preco;
    private String ingredientes; // ou List<String> se quiser avançar depois

    public ItemModel() { }

    public ItemModel(String nome, String descricao, double preco, String ingredientes) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.ingredientes = ingredientes;
    }

    // getters e setters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public String getIngredientes() { return ingredientes; }
    public void setIngredientes(String ingredientes) { this.ingredientes = ingredientes; }
}
