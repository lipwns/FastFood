package faculdade.trabalho.FastFood.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_funcionario")
public class FuncionarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true, length = 11)
    private String cpf;

    private int idade;
    private String sexo;
    private String email;
    private String cargo; // Ex: CAIXA1, CAIXA2, CHAPEIRO, GERENTE
    private String senha;

    public FuncionarioModel() {
    }

    public FuncionarioModel(String nome, String cpf, int idade, String sexo, String email, String cargo, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.sexo = sexo;
        this.email = email;
        this.cargo = cargo;
        this.senha = senha;
    }

    // Getters e setters
    public Long getId() { return id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
