package faculdade.trabalho.FastFood.Controller;

import faculdade.trabalho.FastFood.Model.FuncionarioModel;
import faculdade.trabalho.FastFood.Service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    // Criar novo funcionário
    @PostMapping
    public FuncionarioModel criarFuncionario(@RequestBody FuncionarioModel funcionario) {
        return funcionarioService.salvar(funcionario);
    }

    // Listar todos os funcionários
    @GetMapping
    public List<FuncionarioModel> listarFuncionarios() {
        return funcionarioService.listarTodos();
    }

    // Buscar funcionário por ID
    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioModel> buscarPorId(@PathVariable Long id) {
        return funcionarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Atualizar funcionário existente
    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioModel> atualizarFuncionario(@PathVariable Long id, @RequestBody FuncionarioModel funcionario) {
        return funcionarioService.buscarPorId(id)
                .map(existente -> {
                    existente.setNome(funcionario.getNome());
                    existente.setCpf(funcionario.getCpf());
                    existente.setIdade(funcionario.getIdade());
                    existente.setSexo(funcionario.getSexo());
                    existente.setEmail(funcionario.getEmail());
                    return ResponseEntity.ok(funcionarioService.salvar(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Excluir funcionário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirFuncionario(@PathVariable Long id) {
        if (funcionarioService.buscarPorId(id).isPresent()) {
            funcionarioService.excluir(id);
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }
}