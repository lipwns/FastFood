package faculdade.trabalho.FastFood.Controller;

import faculdade.trabalho.FastFood.Model.ItemIngredienteModel;
import faculdade.trabalho.FastFood.Service.ItemIngredienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item-ingredientes")
@RequiredArgsConstructor
public class ItemIngredienteController {
    private final ItemIngredienteService itemIngredienteService;

    // Criar relação item-ingrediente
    @PostMapping
    public ItemIngredienteModel adicionar(@RequestBody ItemIngredienteModel obj) {
        return itemIngredienteService.adicionar(obj);
    }

    // Listar todas relações
    @GetMapping
    public List<ItemIngredienteModel> listarTodos() {
        return itemIngredienteService.listarTodos();
    }

    // Buscar relação por ID
    @GetMapping("/{id}")
    public ItemIngredienteModel buscarPorId(@PathVariable Long id) {
        return itemIngredienteService.buscarPorId(id);
    }

    // Atualizar quantidade usada no item
    @PutMapping("/{id}")
    public ItemIngredienteModel atualizar(
            @PathVariable Long id,
            @RequestBody ItemIngredienteModel novo
    ) {
        return itemIngredienteService.atualizar(id, novo);
    }

    // Excluir relação
    @DeleteMapping("/{id}")
    public boolean excluir(@PathVariable Long id) {
        return itemIngredienteService.excluir(id);
    }
}