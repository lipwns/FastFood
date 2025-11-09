package faculdade.trabalho.FastFood.Service;

import faculdade.trabalho.FastFood.Model.IngredienteModel;
import faculdade.trabalho.FastFood.Repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    // cadastrar ou atualizar quantidade do ingrediente
    //se ja existir o ingrediente em estoque, ele apenas atualiza a quantidade
    public IngredienteModel salvar(IngredienteModel ingrediente) {
        // Verifica se já existe um ingrediente com o mesmo nome
        Optional<IngredienteModel> existenteOpt = ingredienteRepository.findByNome(ingrediente.getNome());
        if (existenteOpt.isPresent()) {
            IngredienteModel existente = existenteOpt.get();
            existente.setQuantidade(existente.getQuantidade() + ingrediente.getQuantidade());
            return ingredienteRepository.save(existente);
        } else {
            return ingredienteRepository.save(ingrediente);
        }
    }

    // salvar ingrediente sem somar (para o PUT)
    public IngredienteModel atualizar(IngredienteModel ingrediente) {
        return ingredienteRepository.save(ingrediente);
    }

    // listar todos os ingredientes
    public List<IngredienteModel> listarTodos() {
        return ingredienteRepository.findAll();
    }

    // buscar ingrediente por ID
    public Optional<IngredienteModel> buscarPorId(Long id) {
        return ingredienteRepository.findById(id);
    }

    // excluir ingrediente por ID
    public void excluir(Long id) {
        ingredienteRepository.deleteById(id);
    }
}
