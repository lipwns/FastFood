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

    @PostMapping
    public ItemModel criarItem(@RequestBody ItemModel item) {
        return itemService.criarItem(item);
    }

    @GetMapping
    public List<ItemModel> listarTodos() {
        return itemService.listarTodos();
    }

    @GetMapping("/{id}")
    public ItemModel buscarPorId(@PathVariable Long id) {
        return itemService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ItemModel atualizar(@PathVariable Long id, @RequestBody ItemModel item) {
        return itemService.atualizarItem(id, item);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        itemService.excluir(id);
    }
}