package faculdade.trabalho.FastFood.Mapper;

import faculdade.trabalho.FastFood.DTO.FuncionarioDTO;
import faculdade.trabalho.FastFood.Model.FuncionarioModel;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioMapper {
    public FuncionarioModel map(FuncionarioDTO funcionarioDTO) {
        FuncionarioModel funcionarioModel = new FuncionarioModel();
        funcionarioModel.setId(funcionarioDTO.getId());
        funcionarioModel.setNome(funcionarioDTO.getNome());
        funcionarioModel.setCpf(funcionarioDTO.getCpf());
        funcionarioModel.setIdade(funcionarioDTO.getIdade());
        funcionarioModel.setCargo(funcionarioDTO.getCargo());
        funcionarioModel.setSexo(funcionarioDTO.getSexo());
        funcionarioModel.setEmail(funcionarioDTO.getEmail());
        funcionarioModel.setSenha(funcionarioDTO.getSenha());
        return funcionarioModel;
    }

    public FuncionarioDTO map(FuncionarioModel funcionarioModel) {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setId(funcionarioModel.getId());
        funcionarioDTO.setNome(funcionarioModel.getNome());
        funcionarioDTO.setCpf(funcionarioModel.getCpf());
        funcionarioDTO.setIdade(funcionarioModel.getIdade());
        funcionarioDTO.setCargo(funcionarioModel.getCargo());
        funcionarioDTO.setSexo(funcionarioModel.getSexo());
        funcionarioDTO.setEmail(funcionarioModel.getEmail());
        funcionarioDTO.setSenha(funcionarioModel.getSenha());
        return funcionarioDTO;
    }
}
