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

    // Adicionar ingrediente (se existe, soma quantidade)
    public IngredienteModel adicionarIngrediente(IngredienteModel novo) {

        if (novo == null || novo.getNome() == null || novo.getNome().trim().isEmpty()) {
            System.out.println("Ingrediente inválido.");
            return null;
        }

        Optional<IngredienteModel> existenteOpt = ingredienteRepository.findByNomeIgnoreCase(novo.getNome());

        if (existenteOpt.isPresent()) {
            IngredienteModel existente = existenteOpt.get();
            int novaQuantidade = existente.getQuantidade() + novo.getQuantidade();
            existente.setQuantidade(novaQuantidade);
            System.out.println("Quantidade somada. Novo total: " + novaQuantidade);
            return ingredienteRepository.save(existente);
        }

        System.out.println("Ingrediente novo adicionado!");
        return ingredienteRepository.save(novo);
    }

    public List<IngredienteModel> listarTodos() {
        return ingredienteRepository.findAll();
    }

    // buscar por ID
    public IngredienteModel buscarPorId(Long id) {
        if (id == null) {
            System.out.println("ID inválido.");
            return null;
        }

        Optional<IngredienteModel> opt = ingredienteRepository.findById(id);
        return opt.orElse(null);
    }

    public IngredienteModel buscarPorNome(String nome) {
        if (nome == null) return null;
        Optional<IngredienteModel> opt = ingredienteRepository.findByNomeIgnoreCase(nome);
        return opt.orElse(null);
    }

    public IngredienteModel atualizarIngrediente(Long id, IngredienteModel ing) {
        if (id == null) {
            System.out.println("ID inválido.");
            return null;
        }

        Optional<IngredienteModel> existenteOpt = ingredienteRepository.findById(id);

        if (!existenteOpt.isPresent()) {
            System.out.println("Ingrediente não encontrado!");
            return null;
        }

        IngredienteModel existente = existenteOpt.get();
        existente.setNome(ing.getNome());
        existente.setQuantidade(ing.getQuantidade());
        return ingredienteRepository.save(existente);
    }

    public void excluir(Long id) {
        if (id == null) {
            System.out.println("ID inválido para exclusão.");
            return;
        }

        if (!ingredienteRepository.existsById(id)) {
            System.out.println("Ingrediente não encontrado para exclusão.");
            return;
        }

        ingredienteRepository.deleteById(id);
        System.out.println("Ingrediente removido.");
    }
}