package faculdade.trabalho.FastFood.Controller;

import faculdade.trabalho.FastFood.Model.IngredienteModel;
import faculdade.trabalho.FastFood.Service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    // Criar novo ingrediente ou adicionar quantidade
    @PostMapping
    public IngredienteModel criarIngrediente(@RequestBody IngredienteModel ingrediente) {
        // Aqui o service ainda soma caso o ingrediente já exista
        return ingredienteService.salvar(ingrediente);
    }

    // Listar todos os ingredientes
    @GetMapping
    public List<IngredienteModel> listarIngredientes() {
        return ingredienteService.listarTodos();
    }

    // Buscar ingrediente por ID
    @GetMapping("/{id}")
    public ResponseEntity<IngredienteModel> buscarPorId(@PathVariable Long id) {
        return ingredienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Atualizar ingrediente existente (substitui a quantidade)
    @PutMapping("/{id}")
    public ResponseEntity<IngredienteModel> atualizarIngrediente(@PathVariable Long id, @RequestBody IngredienteModel ingrediente) {
        return ingredienteService.buscarPorId(id)
                .map(existente -> {
                    existente.setNome(ingrediente.getNome());
                    existente.setQuantidade(ingrediente.getQuantidade()); // substitui a quantidade
                    ingredienteService.atualizar(existente); // método separado para salvar sem somar
                    return ResponseEntity.ok(existente);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Excluir ingrediente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirIngrediente(@PathVariable Long id) {
        if (ingredienteService.buscarPorId(id).isPresent()) {
            ingredienteService.excluir(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}