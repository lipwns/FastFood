package faculdade.trabalho.FastFood.Mapper;

import faculdade.trabalho.FastFood.Controller.request.IngredienteRequestDTO;
import faculdade.trabalho.FastFood.Controller.response.IngredienteResponseDTO;
import faculdade.trabalho.FastFood.Model.IngredienteModel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class IngredienteMapper {
    public static IngredienteModel toIngredienteModel(IngredienteRequestDTO request) {
        return IngredienteModel.builder()
                .nome(request.nome())
                .quantidade(request.quantidade())
                .build();
    }

    public static IngredienteResponseDTO toIngredienteResponse(IngredienteModel ingrediente) {
        return IngredienteResponseDTO.builder()
                .id(ingrediente.getId())
                .nome(ingrediente.getNome())
                .quantidade(ingrediente.getQuantidade())
                .build();
    }
}
