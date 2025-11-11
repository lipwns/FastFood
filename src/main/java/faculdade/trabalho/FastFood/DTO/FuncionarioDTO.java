package faculdade.trabalho.FastFood.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO {
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @CPF(message = "CPF inválido!")
    private String cpf;

    @NotNull(message = "Idade é obrigatória")
    @Min(value = 1, message = "Idade mínima é 1 ano")
    @Max(value = 120, message = "Idade máxima é 120 anos")
    private int idade;

    private String sexo;

    @NotBlank(message = "Email é obrigatório!")
    @Email(message = "Email inválido!")
    private String email;

    private String cargo; // Ex: CAIXA1, CAIXA2, CHAPEIRO, GERENTE
    private String senha;
}
