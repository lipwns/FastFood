package faculdade.trabalho.FastFood.Model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Table(name = "tb_ingrediente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngredienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int quantidade; // quantidade em estoque
}