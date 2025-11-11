package faculdade.trabalho.FastFood.Service;

import faculdade.trabalho.FastFood.DTO.FuncionarioDTO;
import faculdade.trabalho.FastFood.Mapper.FuncionarioMapper;
import faculdade.trabalho.FastFood.Model.FuncionarioModel;
import faculdade.trabalho.FastFood.Repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioMapper funcionarioMapper;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, FuncionarioMapper funcionarioMapper) {
        this.funcionarioRepository = funcionarioRepository;
        this.funcionarioMapper = funcionarioMapper;
    }

    // Cadastrar funcionário
    public FuncionarioDTO criar(FuncionarioDTO funcionarioDTO) {
        if (funcionarioRepository.existsByCpf(funcionarioDTO.getCpf())) return null;
        FuncionarioModel funcionario = funcionarioMapper.map(funcionarioDTO);
        funcionario = funcionarioRepository.save(funcionario);
        return funcionarioMapper.map(funcionario);
    }

    // Listar todos os funcionários
    public List<FuncionarioDTO> listarTodos() {
        List<FuncionarioModel> funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream().map(funcionarioMapper::map).collect(Collectors.toList());
    }

    // Buscar funcionário por ID
    public FuncionarioDTO buscarPorId(Long id) {
        Optional<FuncionarioModel> funcionarioPorID = funcionarioRepository.findById(id);
        return funcionarioPorID.map(funcionarioMapper::map).orElse(null);
    }

    // Atualizar funcionário
    public FuncionarioDTO atualizarFuncionario(Long id, FuncionarioDTO funcionarioDTO) {
        Optional<FuncionarioModel> funcionarioExistente = funcionarioRepository.findById(id);
        if (funcionarioExistente.isPresent()) {
            FuncionarioModel funcionarioAtualizado = funcionarioMapper.map(funcionarioDTO);
            funcionarioAtualizado.setId(id);

            FuncionarioModel funcionarioSalvo = funcionarioRepository.save(funcionarioAtualizado);
            return funcionarioMapper.map(funcionarioSalvo);
        }
        return null;
    }

    // Excluir funcionário por ID
    public void excluir(Long id) {
        funcionarioRepository.deleteById(id);
    }
}