package faculdade.trabalho.FastFood.Service;

import faculdade.trabalho.FastFood.Model.ItemModel;
import faculdade.trabalho.FastFood.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public ItemModel salvar(ItemModel item) {
        return itemRepository.save(item);
    }

    public List<ItemModel> listarTodos() {
        return itemRepository.findAll();
    }

    public ItemModel buscarPorId(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public ItemModel atualizarItem(Long id, ItemModel novoItem) {
        ItemModel existente = buscarPorId(id);
        if (existente != null) {
            existente.setNome(novoItem.getNome());
            existente.setDescricao(novoItem.getDescricao());
            existente.setPreco(novoItem.getPreco());
            existente.setIngredientes(novoItem.getIngredientes());
            return itemRepository.save(existente);
        }
        return null;
    }

    public boolean excluir(Long id) {
        ItemModel item = buscarPorId(id);
        if (item != null) {
            itemRepository.delete(item);
            return true;
        }
        return false;
    }
}