package faculdade.trabalho.FastFood.Controller;

import faculdade.trabalho.FastFood.Model.PedidoModel;
import faculdade.trabalho.FastFood.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        return pedidoService.salvar(pedido);
    }

    // Listar todos os pedidos
    @GetMapping
    public List<PedidoModel> listarPedidos() {
        return pedidoService.listarTodos();
    }

    // Buscar pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<PedidoModel> buscarPorId(@PathVariable Long id) {
        return pedidoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Atualizar pedido existente
    @PutMapping("/{id}")
    public ResponseEntity<PedidoModel> atualizarPedido(@PathVariable Long id, @RequestBody PedidoModel pedido) {
        return pedidoService.buscarPorId(id)
                .map(existente -> {
                    existente.setNome(pedido.getNome());
                    existente.setItens(pedido.getItens());
                    existente.setQuantidade(pedido.getQuantidade());
                    existente.setPrecoTotal(pedido.getPrecoTotal());
                    existente.setStatus(pedido.getStatus());
                    existente.setFormaPagamento(pedido.getFormaPagamento());
                    return ResponseEntity.ok(pedidoService.salvar(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Excluir pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPedido(@PathVariable Long id) {
        if (pedidoService.buscarPorId(id).isPresent()) {
            pedidoService.excluir(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
