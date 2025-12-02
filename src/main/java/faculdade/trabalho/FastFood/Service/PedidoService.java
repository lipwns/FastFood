package faculdade.trabalho.FastFood.Service;

import faculdade.trabalho.FastFood.Model.PedidoModel;
import faculdade.trabalho.FastFood.Model.ItemPedidoModel;
import faculdade.trabalho.FastFood.Model.ItemIngredienteModel;
import faculdade.trabalho.FastFood.Model.IngredienteModel;
import faculdade.trabalho.FastFood.Model.ItemModel;
import faculdade.trabalho.FastFood.Repository.PedidoRepository;
import faculdade.trabalho.FastFood.Repository.IngredienteRepository;
import faculdade.trabalho.FastFood.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Autowired
    private ItemRepository itemRepository;

    // Criar pedido
    public PedidoModel criarPedido(PedidoModel pedido) {
        double total = 0.0;

        for (ItemPedidoModel ip : pedido.getItens()) {
            // Buscar o item completo no banco
            Optional<ItemModel> opItem = itemRepository.findById(ip.getItem().getId());
            if (opItem.isEmpty()) {
                System.out.println("Item não encontrado no banco: " + ip.getItem().getId());
                continue;
            }

            ItemModel itemCompleto = opItem.get();
            ip.setItem(itemCompleto); // substitui o item enviado pelo item completo

            // Define preço unitário e subtotal do item
            ip.setPrecoUnitario(itemCompleto.getPreco());
            ip.setSubtotal(ip.getPrecoUnitario() * ip.getQuantidade());
            ip.setPedido(pedido); // relaciona item ao pedido

            // Reduz ingredientes do item corretamente
            for (ItemIngredienteModel iIng : itemCompleto.getIngredientes()) {
                IngredienteModel ing = iIng.getIngrediente();
                int totalConsumido = iIng.getQuantidade() * ip.getQuantidade();
                ing.setQuantidade(ing.getQuantidade() - totalConsumido);
                ingredienteRepository.save(ing);
            }

            total += ip.getSubtotal();
        }

        pedido.setPrecoTotal(total);
        pedido.setStatus("em espera");
        pedido.setDataHora(LocalDateTime.now());

        return pedidoRepository.save(pedido);
    }

    // Buscar por ID
    public Optional<PedidoModel> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    // Listar todos os pedidos
    public List<PedidoModel> listarTodos() {
        return pedidoRepository.findAll();
    }

    // Cancelar pedido
    public void cancelarPedido(Long id) {
        Optional<PedidoModel> opPedido = pedidoRepository.findById(id);

        if (opPedido.isPresent()) {
            PedidoModel pedido = opPedido.get();

            // Só devolve ingredientes se estiver em espera
            if (pedido.getStatus().equalsIgnoreCase("em espera")) {
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
        String status = pedido.getStatus();
        if (status.equalsIgnoreCase("em espera")) {
            pedido.setStatus("em preparo");
        } else if (status.equalsIgnoreCase("em preparo")) {
            pedido.setStatus("pronto");
        } else if (status.equalsIgnoreCase("pronto")) {
            pedido.setStatus("entregue");
        }

        pedidoRepository.save(pedido);
    }

    // Voltar status
    public void voltarStatus(PedidoModel pedido) {
        String status = pedido.getStatus();
        if (status.equalsIgnoreCase("entregue")) {
            pedido.setStatus("pronto");
        } else if (status.equalsIgnoreCase("pronto")) {
            pedido.setStatus("em preparo");
        } else if (status.equalsIgnoreCase("em preparo")) {
            pedido.setStatus("em espera");
        }

        pedidoRepository.save(pedido);
    }
}