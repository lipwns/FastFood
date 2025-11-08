package faculdade.trabalho.FastFood.Service;

import faculdade.trabalho.FastFood.Model.FuncionarioModel;
import faculdade.trabalho.FastFood.Repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // Cadastrar ou atualizar funcionário
    public FuncionarioModel salvar(FuncionarioModel funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    // Listar todos os funcionários
    public List<FuncionarioModel> listarTodos() {
        return funcionarioRepository.findAll();
    }

    // Buscar funcionário por ID
    public Optional<FuncionarioModel> buscarPorId(Long id) {
        return funcionarioRepository.findById(id);
    }

    // Excluir funcionário por ID
    public void excluir(Long id) {
        funcionarioRepository.deleteById(id);
    }
}