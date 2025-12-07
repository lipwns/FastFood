package faculdade.trabalho.FastFood.Controller.UI;

import faculdade.trabalho.FastFood.Model.IngredienteModel;
import faculdade.trabalho.FastFood.Service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/fastfood")
@RequiredArgsConstructor
public class IngredienteControllerUI {
    private final IngredienteService ingredienteService;

    @GetMapping("/listar/ingredientes")
    public String listarIngredientes(Model model) {
        List<IngredienteModel> ingredientes = ingredienteService.listarTodos();
        model.addAttribute("ingredient", ingredientes);
        return "Ingredientes/listar";
    }

    @GetMapping("/deletar/ingredientes/{id}")
    public String deletarIngredienteID(@PathVariable Long id) {
        ingredienteService.excluir(id);
        return "redirect:/fastfood/listar/ingredientes";
    }

    @GetMapping("/alterar/{id}")
    public String exibirFormularioAlterar(@PathVariable Long id, Model model) {
        IngredienteModel ingrediente = ingredienteService.buscarPorId(id);
        if (ingrediente != null) {
            model.addAttribute("ingrediente", ingrediente);
            return "Ingredientes/alterar";
        }
        return "redirect:/fastfood/listar/ingredientes";
    }

    @PostMapping("/atualizar")
    public String atualizar(@ModelAttribute IngredienteModel ingredienteModel, Model model) {
        IngredienteModel ingredienteAtualizado = ingredienteService.atualizarIngrediente(ingredienteModel.getId(), ingredienteModel);

        if (ingredienteAtualizado != null) {
            model.addAttribute("mensagem", "Ingrediente atualizado com sucesso!");
            return "redirect:/fastfood/listar/ingredientes";
        }

        model.addAttribute("mensagem", "Erro ao atualizar ingrediente!");
        return "Ingredientes/alterar";
    }

    @GetMapping("/criar")
    public String mostrarFormularioAdicionar(Model model) {
        model.addAttribute("ingrediente", new IngredienteModel());
        return "Ingredientes/adicionar";
    }

    @PostMapping("/salvar")
    public String salvarNinja(@ModelAttribute("ingrediente") IngredienteModel ingrediente, BindingResult result, Model model) {
        if (result.hasErrors()) return "Ninja/adicionarNinja";
        try {
            IngredienteModel ingredienteSalvo = ingredienteService.adicionarIngrediente(ingrediente);

            if (ingredienteSalvo == null) {
                model.addAttribute("mensagem", "Ocorreu um erro!");
                return "Ninja/adicionarNinja";
            }

            model.addAttribute("mensagem", "Ninja cadastrado com sucesso!");
            return "redirect:/ninjas/ui/listar";

        } catch (DataIntegrityViolationException e) {
            model.addAttribute("mensagem", "Ocorreu um erro!");
            return "Ingredientes/adicionar";
        } catch (Exception e) {
            model.addAttribute("mensagem", "Erro ao criar ingrediente: " + e.getMessage());
            return "Ingredientes/adicionar";
        }
    }

}
