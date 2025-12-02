package faculdade.trabalho.FastFood.Service;

import faculdade.trabalho.FastFood.Model.ItemPedidoModel;
import faculdade.trabalho.FastFood.Repository.ItemPedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;

    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }

    // Criar item do pedido
    public ItemPedidoModel criarItemPedido(ItemPedidoModel itemPedido) {

        if (itemPedido == null || itemPedido.getItem() == null || itemPedido.getQuantidade() <= 0) {
            System.out.println("ItemPedido inválido.");
            return null;
        }

        double total = itemPedido.getItem().getPreco() * itemPedido.getQuantidade();
        itemPedido.setPrecoUnitario(itemPedido.getItem().getPreco());
        itemPedido.setSubtotal(total);

        System.out.println("Item do pedido criado. Preço total do item: " + total);

        return itemPedidoRepository.save(itemPedido);
    }

    public ItemPedidoModel buscarPorId(Long id) {
        return itemPedidoRepository.findById(id).orElse(null);
    }

    public List<ItemPedidoModel> listarTodos() {
        return itemPedidoRepository.findAll();
    }

    public ItemPedidoModel atualizarItem(Long id, ItemPedidoModel dados) {
        ItemPedidoModel existente = buscarPorId(id);
        if (existente == null) {
            System.out.println("ItemPedido não encontrado para atualizar.");
            return null;
        }

        if (dados.getQuantidade() > 0) {
            existente.setQuantidade(dados.getQuantidade());
        }

        // Recalcula subtotal
        double novoTotal = existente.getItem().getPreco() * existente.getQuantidade();
        existente.setSubtotal(novoTotal);
        existente.setPrecoUnitario(existente.getItem().getPreco());

        System.out.println("ItemPedido atualizado. Novo subtotal: " + novoTotal);

        return itemPedidoRepository.save(existente);
    }

    public boolean excluir(Long id) {
        ItemPedidoModel item = buscarPorId(id);
        if (item == null) {
            System.out.println("ItemPedido não encontrado para exclusão.");
            return false;
        }
        itemPedidoRepository.delete(item);
        System.out.println("ItemPedido removido com sucesso.");
        return true;
    }
}