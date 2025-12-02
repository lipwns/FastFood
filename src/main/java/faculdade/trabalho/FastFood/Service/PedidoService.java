package faculdade.trabalho.FastFood.Service;

import faculdade.trabalho.FastFood.Model.ItemPedidoModel;
import faculdade.trabalho.FastFood.Model.PedidoModel;
import faculdade.trabalho.FastFood.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    // Criar ou atualizar pedido
    public PedidoModel salvar(PedidoModel pedido) {

        if (pedido == null) {
            System.out.println("Pedido inválido.");
            return null;
        }

        // Evitar null pointer
        if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
            pedido.setPrecoTotal(0);
            return pedidoRepository.save(pedido);
        }

        // Recalcula o preço total baseado nos itens
        double total = 0;
        for (ItemPedidoModel item : pedido.getItens()) {
            if (item.getSubtotal() == 0) {
                double valor = item.getPrecoUnitario() * item.getQuantidade();
                item.setSubtotal(valor);
            }
            total += item.getSubtotal();
        }
        pedido.setPrecoTotal(total);

        System.out.println("Preço total do pedido atualizado: " + total);

        return pedidoRepository.save(pedido);
    }

    public List<PedidoModel> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<PedidoModel> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public void excluir(Long id) {
        if (!pedidoRepository.existsById(id)) {
            System.out.println("Pedido não encontrado para exclusão.");
            return;
        }
        pedidoRepository.deleteById(id);
        System.out.println("Pedido removido com sucesso.");
    }

    // Avançar status do pedido
    public void avancarStatus(PedidoModel pedido) {
        if (pedido.getStatus().equals("em espera")) {
            pedido.setStatus("em preparo");
        } else if (pedido.getStatus().equals("em preparo")) {
            pedido.setStatus("concluido");
        }
        salvar(pedido);
        System.out.println("Status do pedido atualizado para: " + pedido.getStatus());
    }

    // Voltar status do pedido
    public void voltarStatus(PedidoModel pedido) {
        if (pedido.getStatus().equals("em preparo")) {
            pedido.setStatus("em espera");
            salvar(pedido);
            System.out.println("Status do pedido retornado para: " + pedido.getStatus());
        }
    }
}