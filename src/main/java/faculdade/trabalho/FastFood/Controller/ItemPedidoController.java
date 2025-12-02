package faculdade.trabalho.FastFood.Controller;

import faculdade.trabalho.FastFood.Model.ItemPedidoModel;
import faculdade.trabalho.FastFood.Service.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens-pedido")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoService itemPedidoService;

    // Criar um novo item do pedido
    @PostMapping
    public ItemPedidoModel criarItemPedido(@RequestBody ItemPedidoModel itemPedido) {
        return itemPedidoService.criarItemPedido(itemPedido);
    }

    // Listar todos os itens de pedidos
    @GetMapping
    public List<ItemPedidoModel> listarTodos() {
        return itemPedidoService.listarTodos();
    }

    // Buscar item do pedido por ID
    @GetMapping("/{id}")
    public ItemPedidoModel buscarPorId(@PathVariable Long id) {
        return itemPedidoService.buscarPorId(id);
    }

    // Atualizar item do pedido
    @PutMapping("/{id}")
    public ItemPedidoModel atualizarItem(@PathVariable Long id, @RequestBody ItemPedidoModel dados) {
        return itemPedidoService.atualizarItem(id, dados);
    }

    // Excluir item do pedido
    @DeleteMapping("/{id}")
    public String excluirItem(@PathVariable Long id) {
        boolean removido = itemPedidoService.excluir(id);
        return removido ? "Item do pedido removido com sucesso." : "Item do pedido não encontrado.";
    }
}