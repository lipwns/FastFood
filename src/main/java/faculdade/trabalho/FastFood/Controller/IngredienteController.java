package faculdade.trabalho.FastFood.Controller;

import faculdade.trabalho.FastFood.Model.IngredienteModel;
import faculdade.trabalho.FastFood.Service.IngredienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredientes")
@RequiredArgsConstructor
public class IngredienteController {
    private final IngredienteService ingredienteService;

    // Adicionar ingrediente (soma quantidade se já existir)
    @PostMapping
    public IngredienteModel adicionarIngrediente(@RequestBody IngredienteModel ingrediente) {
        return ingredienteService.adicionarIngrediente(ingrediente);
    }

    // Listar todos os ingredientes
    @GetMapping
    public List<IngredienteModel> listarTodos() {
        return ingredienteService.listarTodos();
    }

    // Buscar ingrediente por ID
    @GetMapping("/{id}")
    public IngredienteModel buscarPorId(@PathVariable Long id) {
        return ingredienteService.buscarPorId(id);
    }

    // Buscar ingrediente por nome
    @GetMapping("/buscar")
    public IngredienteModel buscarPorNome(@RequestParam String nome) {
        return ingredienteService.buscarPorNome(nome);
    }

    // Atualizar ingrediente
    @PutMapping("/{id}")
    public IngredienteModel atualizarIngrediente(@PathVariable Long id, @RequestBody IngredienteModel ingrediente) {
        return ingredienteService.atualizarIngrediente(id, ingrediente);
    }

    // Excluir ingrediente
    @DeleteMapping("/{id}")
    public String excluirIngrediente(@PathVariable Long id) {
        ingredienteService.excluir(id);
        return "Ingrediente removido.";
    }
}