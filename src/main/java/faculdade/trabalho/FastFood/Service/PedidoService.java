package faculdade.trabalho.FastFood.Service;

import faculdade.trabalho.FastFood.Model.*;
import faculdade.trabalho.FastFood.Repository.PedidoRepository;
import faculdade.trabalho.FastFood.Repository.IngredienteRepository;
import faculdade.trabalho.FastFood.Repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final IngredienteRepository ingredienteRepository;
    private final ItemRepository itemRepository;

    // Criar pedido com validação de estoque
    public PedidoModel criarPedido(PedidoModel pedido) {

        if (pedido == null || pedido.getItens() == null) {
            System.out.println("Pedido inválido: lista de itens nula.");
            return null;
        }

        // Remove itens que não têm produto selecionado ou quantidade inválida
        pedido.getItens().removeIf(ip -> ip == null || ip.getItem() == null || ip.getQuantidade() <= 0);

        if (pedido.getItens().isEmpty()) {
            System.out.println("Nenhum item válido no pedido.");
            return null;
        }

        double total = 0.0;

        // Verifica estoque antes de criar
        for (ItemPedidoModel ip : pedido.getItens()) {
            Optional<ItemModel> opItem = itemRepository.findById(ip.getItem().getId());
            if (opItem.isEmpty()) {
                System.out.println("Item não encontrado no banco: " + ip.getItem().getId());
                return null;
            }

            ItemModel itemCompleto = opItem.get();

            for (ItemIngredienteModel iIng : itemCompleto.getIngredientes()) {
                IngredienteModel ing = iIng.getIngrediente();
                int totalNecessario = iIng.getQuantidade() * ip.getQuantidade();
                if (ing.getQuantidade() < totalNecessario) {
                    System.out.println("Estoque insuficiente para: " + ing.getNome());
                    return null;
                }
            }
        }

        // Reduz estoque e calcula total
        for (ItemPedidoModel ip : pedido.getItens()) {
            ItemModel itemCompleto = itemRepository.findById(ip.getItem().getId()).get();
            ip.setItem(itemCompleto);
            ip.setPrecoUnitario(itemCompleto.getPreco());
            ip.setSubtotal(ip.getPrecoUnitario() * ip.getQuantidade());
            ip.setPedido(pedido);

            for (ItemIngredienteModel iIng : itemCompleto.getIngredientes()) {
                IngredienteModel ing = iIng.getIngrediente();
                int totalConsumido = iIng.getQuantidade() * ip.getQuantidade();
                ing.setQuantidade(ing.getQuantidade() - totalConsumido);
                ingredienteRepository.save(ing);
            }

            total += ip.getSubtotal();
        }

        pedido.setPrecoTotal(total);
        pedido.setStatus(StatusPedido.EM_ESPERA);
        pedido.setDataHora(LocalDateTime.now());

        return pedidoRepository.save(pedido);
    }

    // Salvar pedido atualizado sem tocar no estoque
    public PedidoModel salvarPedido(PedidoModel pedido) {
        double total = 0.0;
        for (ItemPedidoModel ip : pedido.getItens()) {
            if (ip.getItem() != null) {
                ip.setSubtotal(ip.getPrecoUnitario() * ip.getQuantidade());
            }
            total += ip.getSubtotal();
        }
        pedido.setPrecoTotal(total);

        return pedidoRepository.save(pedido);
    }

    // Buscar por ID
    public Optional<PedidoModel> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    // Listar todos os pedidos **ativos** (exclui entregues)
    public List<PedidoModel> listarTodos() {
        return pedidoRepository.findAll()
                .stream()
                .filter(p -> p.getStatus() != StatusPedido.ENTREGUE)
                .toList();
    }

    // Cancelar pedido
    public void cancelarPedido(Long id) {
        Optional<PedidoModel> opPedido = pedidoRepository.findById(id);

        if (opPedido.isPresent()) {
            PedidoModel pedido = opPedido.get();

            if (pedido.getStatus() == StatusPedido.EM_ESPERA) {
                for (ItemPedidoModel ip : pedido.getItens()) {
                    for (ItemIngredienteModel iIng : ip.getItem().getIngredientes()) {
                        IngredienteModel ing = iIng.getIngrediente();
                        int totalDevolvido = iIng.getQuantidade() * ip.getQuantidade();
                        ing.setQuantidade(ing.getQuantidade() + totalDevolvido);
                        ingredienteRepository.save(ing);
                    }
                }
            }

            pedidoRepository.delete(pedido);
        }
    }

    // Avançar status
    public void avancarStatus(PedidoModel pedido) {
        switch (pedido.getStatus()) {
            case EM_ESPERA -> pedido.setStatus(StatusPedido.EM_PREPARO);
            case EM_PREPARO -> pedido.setStatus(StatusPedido.PRONTO);
            case PRONTO -> pedido.setStatus(StatusPedido.ENTREGUE);
        }
        pedidoRepository.save(pedido);
    }

    // Voltar status
    public void voltarStatus(PedidoModel pedido) {
        switch (pedido.getStatus()) {
            case ENTREGUE -> pedido.setStatus(StatusPedido.PRONTO);
            case PRONTO -> pedido.setStatus(StatusPedido.EM_PREPARO);
            case EM_PREPARO -> pedido.setStatus(StatusPedido.EM_ESPERA);
        }
        pedidoRepository.save(pedido);
    }
}