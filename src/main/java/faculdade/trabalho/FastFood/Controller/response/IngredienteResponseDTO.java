package faculdade.trabalho.FastFood.Controller.response;

import lombok.Builder;

@Builder
public record IngredienteResponseDTO(Long id, String nome, int quantidade) {
}
