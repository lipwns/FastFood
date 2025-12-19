package faculdade.trabalho.FastFood.Controller;

import faculdade.trabalho.FastFood.Controller.request.IngredienteRequestDTO;
import faculdade.trabalho.FastFood.Controller.response.IngredienteResponseDTO;
import faculdade.trabalho.FastFood.Mapper.IngredienteMapper;
import faculdade.trabalho.FastFood.Model.IngredienteModel;
import faculdade.trabalho.FastFood.Service.IngredienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ingredientes")
@RequiredArgsConstructor
public class IngredienteController {
    private final IngredienteService ingredienteService;

    // Adicionar ingrediente (soma quantidade se já existir)
    @PostMapping
    public ResponseEntity<IngredienteResponseDTO> adicionarIngrediente(@RequestBody IngredienteRequestDTO request) {
        IngredienteModel ingAdicionado = ingredienteService.adicionarIngrediente(IngredienteMapper.toIngredienteModel(request));
        return ResponseEntity.ok(IngredienteMapper.toIngredienteResponse(ingAdicionado));
    }

    // Listar todos os ingredientes
    @GetMapping
    public ResponseEntity<List<IngredienteResponseDTO>> listarTodos() {
        return ResponseEntity.ok(ingredienteService.listarTodos().stream()
                .map(IngredienteMapper::toIngredienteResponse)
                .toList());
    }

    // Buscar ingrediente por ID
    @GetMapping("/{id}")
    public ResponseEntity<IngredienteResponseDTO> buscarPorId(@PathVariable Long id) {
        return ingredienteService.buscarPorId(id)
                .map(ingrediente -> ResponseEntity.ok(IngredienteMapper.toIngredienteResponse(ingrediente)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar ingrediente por nome
    @GetMapping("/buscar")
    public ResponseEntity<IngredienteResponseDTO> buscarPorNome(@RequestParam String nome) {
        return ingredienteService.buscarPorNome(nome)
                .map(ingrediente -> ResponseEntity.ok(IngredienteMapper.toIngredienteResponse(ingrediente)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Atualizar ingrediente
    @PutMapping("/{id}")
    public ResponseEntity<IngredienteResponseDTO> atualizarIngrediente(@PathVariable Long id, @RequestBody IngredienteRequestDTO request) {
        return ingredienteService.atualizarIngrediente(id, IngredienteMapper.toIngredienteModel(request))
                .map(ingrediente -> ResponseEntity.ok(IngredienteMapper.toIngredienteResponse(ingrediente)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Excluir ingrediente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirIngrediente(@PathVariable Long id) {
        Optional<IngredienteModel> optIngrediente = ingredienteService.buscarPorId(id);
        if (optIngrediente.isPresent()) {
            ingredienteService.excluir(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}