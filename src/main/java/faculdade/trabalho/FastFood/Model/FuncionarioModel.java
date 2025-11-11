package faculdade.trabalho.FastFood.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_funcionario")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
