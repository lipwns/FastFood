package faculdade.trabalho.FastFood.Controller;

import faculdade.trabalho.FastFood.Model.ItemModel;
import faculdade.trabalho.FastFood.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Criar novo item
    @PostMapping
    public ItemModel criarItem(@RequestBody ItemModel item) {
        System.out.println("Criando item: " + item.getNome());
        return itemService.salvar(item);
    }

    // Listar todos os itens
    @GetMapping
    public List<ItemModel> listarItens() {
        System.out.println("Listando todos os itens...");
        return itemService.listarTodos();
    }

    // Buscar item por ID
    @GetMapping("/{id}")
    public ItemModel buscarPorId(@PathVariable Long id) {
        ItemModel item = itemService.buscarPorId(id);
        if (item != null) {
            System.out.println("Item encontrado: " + item.getNome());
            return item;
        } else {
            System.out.println("Item não encontrado!");
            return null;
        }
    }

    // Atualizar item
    @PutMapping("/{id}")
    public ItemModel atualizarItem(@PathVariable Long id, @RequestBody ItemModel novoItem) {
        ItemModel atualizado = itemService.atualizarItem(id, novoItem);
        if (atualizado != null) {
            System.out.println("Item atualizado com sucesso!");
        } else {
            System.out.println("Item não encontrado para atualização!");
        }
        return atualizado;
    }

    // Excluir item
    @DeleteMapping("/{id}")
    public void excluirItem(@PathVariable Long id) {
        boolean excluido = itemService.excluir(id);
        if (excluido) {
            System.out.println("Item excluído com sucesso!");
        } else {
            System.out.println("Item não encontrado para exclusão!");
        }
    }
}