package faculdade.trabalho.FastFood.Controller;

import faculdade.trabalho.FastFood.DTO.FuncionarioDTO;
import faculdade.trabalho.FastFood.Service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    // Criar novo funcionário
    @PostMapping
    public ResponseEntity<String> criarFuncionario(@Valid @RequestBody FuncionarioDTO funcionario) {
        FuncionarioDTO novoFuncionario = funcionarioService.criar(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Funcionário \"" + novoFuncionario.getNome() + "\" criado com sucesso!");
    }

    // Listar todos os funcionários
    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> listarFuncionarios() {
        List<FuncionarioDTO> funcionarios = funcionarioService.listarTodos();
        return ResponseEntity.ok(funcionarios);
    }

    // Buscar funcionário por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        FuncionarioDTO funcionario = funcionarioService.buscarPorId(id);
        if (funcionario != null) return ResponseEntity.ok(funcionario);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário com ID " + id + " não encontrado!");
    }

    // Atualizar funcionário existente
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarFuncionario(@PathVariable Long id, @Valid @RequestBody FuncionarioDTO funcionarioAtualizado) {
        FuncionarioDTO funcionario = funcionarioService.atualizarFuncionario(id, funcionarioAtualizado);
        if (funcionario != null) return ResponseEntity.ok(funcionario);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário com o ID " + id + " não encontrado!");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcialmenteFuncionario(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        FuncionarioDTO funcionario = funcionarioService.atualizarFuncionarioPatch(id, fields);
        if (funcionario != null) return ResponseEntity.ok(funcionario);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário com o ID " + id + " não encontrado!");
    }

    // Excluir funcionário
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirFuncionario(@PathVariable Long id) {
        FuncionarioDTO funcionario = funcionarioService.buscarPorId(id);
        if (funcionario != null) {
            funcionarioService.excluir(id);
            return ResponseEntity.ok("Funcionário \"" + funcionario.getNome() + "\" deletado com sucesso"); // 204
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário com ID " + id + " não encontrado!"); // 404
        }
    }
}