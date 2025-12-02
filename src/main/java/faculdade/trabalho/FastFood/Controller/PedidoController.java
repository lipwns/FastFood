package faculdade.trabalho.FastFood.Controller;

import faculdade.trabalho.FastFood.Model.PedidoModel;
import faculdade.trabalho.FastFood.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Criar novo pedido
    @PostMapping
    public PedidoModel criarPedido(@RequestBody PedidoModel pedido) {
        return pedidoService.criarPedido(pedido);
    }

    // Listar todos os pedidos
    @GetMapping
    public List<PedidoModel> listarPedidos() {
        return pedidoService.listarTodos();
    }

    // Buscar pedido por ID
    @GetMapping("/{id}")
    public PedidoModel buscarPedidoPorId(@PathVariable Long id) {
        return pedidoService.buscarPorId(id).orElse(null);
    }

    // Atualizar pedido existente
    @PutMapping("/{id}")
    public PedidoModel atualizarPedido(@PathVariable Long id, @RequestBody PedidoModel pedidoAtualizado) {
        PedidoModel existente = pedidoService.buscarPorId(id).orElse(null);

        if (existente == null) {
            System.out.println("Pedido não encontrado!");
            return null;
        }

        // Atualiza campos permitidos
        existente.setNome(pedidoAtualizado.getNome());
        existente.setFormaPagamento(pedidoAtualizado.getFormaPagamento());
        existente.setItens(pedidoAtualizado.getItens());

        // Salva e recalcula o preço total
        return pedidoService.criarPedido(existente);
    }

    // Excluir pedido
    @DeleteMapping("/{id}")
    public String excluirPedido(@PathVariable Long id) {
        if (pedidoService.buscarPorId(id).isPresent()) {
            pedidoService.cancelarPedido(id);
            return "Pedido removido com sucesso.";
        } else {
            return "Pedido não encontrado.";
        }
    }

    // Avançar status do pedido
    @PutMapping("/{id}/avancar")
    public PedidoModel avancarStatus(@PathVariable Long id) {
        PedidoModel pedido = pedidoService.buscarPorId(id).orElse(null);

        if (pedido == null) {
            System.out.println("Pedido não encontrado!");
            return null;
        }

        pedidoService.avancarStatus(pedido);
        return pedido;
    }

    // Voltar status do pedido
    @PutMapping("/{id}/voltar")
    public PedidoModel voltarStatus(@PathVariable Long id) {
        PedidoModel pedido = pedidoService.buscarPorId(id).orElse(null);

        if (pedido == null) {
            System.out.println("Pedido não encontrado!");
            return null;
        }

        pedidoService.voltarStatus(pedido);
        return pedido;
    }
}