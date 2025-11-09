package faculdade.trabalho.FastFood.Controller;

import faculdade.trabalho.FastFood.Model.ItemModel;
import faculdade.trabalho.FastFood.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        return itemService.salvar(item);
    }

    // Listar todos os itens
    @GetMapping
    public List<ItemModel> listarItens() {
        return itemService.listarTodos();
    }

    // Buscar item por ID
    @GetMapping("/{id}")
    public ResponseEntity<ItemModel> buscarPorId(@PathVariable Long id) {
        return itemService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Atualizar item existente
    @PutMapping("/{id}")
    public ResponseEntity<ItemModel> atualizarItem(@PathVariable Long id, @RequestBody ItemModel item) {
        return itemService.buscarPorId(id)
                .map(existente -> {
                    existente.setNome(item.getNome());
                    existente.setDescricao(item.getDescricao());
                    existente.setPreco(item.getPreco());
                    existente.setIngredientes(item.getIngredientes());
                    return ResponseEntity.ok(itemService.salvar(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Excluir item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirItem(@PathVariable Long id) {
        if (itemService.buscarPorId(id).isPresent()) {
            itemService.excluir(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}