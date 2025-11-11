package faculdade.trabalho.FastFood.Service;

import faculdade.trabalho.FastFood.Model.ItemIngredienteModel;
import faculdade.trabalho.FastFood.Repository.ItemIngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemIngredienteService {

    @Autowired
    private ItemIngredienteRepository itemIngredienteRepository;

    public ItemIngredienteModel salvar(ItemIngredienteModel itemIngrediente) {
        return itemIngredienteRepository.save(itemIngrediente);
    }

    public List<ItemIngredienteModel> listarTodos() {
        return itemIngredienteRepository.findAll();
    }

    public Optional<ItemIngredienteModel> buscarPorId(Long id) {
        return itemIngredienteRepository.findById(id);
    }

    public void excluir(Long id) {
        itemIngredienteRepository.deleteById(id);
    }
}