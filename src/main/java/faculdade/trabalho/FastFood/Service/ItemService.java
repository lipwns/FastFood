package faculdade.trabalho.FastFood.Service;

import faculdade.trabalho.FastFood.Model.ItemModel;
import faculdade.trabalho.FastFood.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    // Salvar ou atualizar item
    public ItemModel salvar(ItemModel item) {
        return itemRepository.save(item);
    }

    // Listar todos os itens
    public List<ItemModel> listarTodos() {
        return itemRepository.findAll();
    }

    // Buscar item por ID
    public Optional<ItemModel> buscarPorId(Long id) {
        return itemRepository.findById(id);
    }

    // Excluir item por ID
    public void excluir(Long id) {
        itemRepository.deleteById(id);
    }
}