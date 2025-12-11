package faculdade.trabalho.FastFood.Controller.UI;

import faculdade.trabalho.FastFood.Model.IngredienteModel;
import faculdade.trabalho.FastFood.Model.ItemModel;
import faculdade.trabalho.FastFood.Service.IngredienteService;
import faculdade.trabalho.FastFood.Service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/fastfood/cardapio")
@RequiredArgsConstructor
public class CardapioControllerUI {
    private final ItemService itemService;
    private final IngredienteService ingredienteService;

    @GetMapping("/listar")
    public String listarCardapio(Model model) {
        List<ItemModel> itens = itemService.listarTodos();
        model.addAttribute("itens", itens);
        return "Cardapio/listar";
    }

    @GetMapping("/deletar/{id}")
    public String deletarItemID(@PathVariable Long id) {
        itemService.excluir(id);
        return "redirect:/fastfood/cardapio/listar";
    }

    @GetMapping("/alterar/{id}")
    public String exibirFormularioAlterar(@PathVariable Long id, Model model) {
        ItemModel item = itemService.buscarPorId(id);
        if (item != null) {
            model.addAttribute("item", item);
            List<IngredienteModel> ingredientes = ingredienteService.listarTodos();
            model.addAttribute("ingredientesDisponiveis", ingredientes);
            return "Cardapio/alterar";
        }
        return "redirect:/fastfood/cardapio/listar";
    }

    @PostMapping("/atualizar")
    public String atualizar(@ModelAttribute ItemModel itemModel, Model model) {
        ItemModel itemAtualizado = itemService.atualizarItem(itemModel.getId(), itemModel);

        if (itemAtualizado != null) {
            model.addAttribute("mensagem", "Item atualizado com sucesso!");
            return "redirect:/fastfood/cardapio/listar";
        }

        model.addAttribute("mensagem", "Erro ao atualizar item! Verifique se os ingredientes são válidos!");

        // CORREÇÃO: Devolver o objeto 'item' preenchido para o formulário não quebrar
        model.addAttribute("item", itemModel);

        // CORREÇÃO: Carregar a lista de ingredientes novamente para os checkboxes funcionarem
        List<IngredienteModel> ingredientes = ingredienteService.listarTodos();
        model.addAttribute("ingredientesDisponiveis", ingredientes);

        return "Cardapio/alterar";
    }

    @GetMapping("/criar")
    public String mostrarFormularioAdicionar(Model model) {
        model.addAttribute("item", new ItemModel());

        List<IngredienteModel> ingredientes = ingredienteService.listarTodos();
        model.addAttribute("ingredientesDisponiveis", ingredientes);

        return "Cardapio/adicionar";
    }

    @PostMapping("/salvar")
    public String salvarItem(@ModelAttribute("item") ItemModel item,
                             @RequestParam(required = false) List<Long> ingredientesSelecionados,
                             BindingResult result,
                             Model model) {

        // Validação de tamanho da descrição
        if (item.getDescricao() != null && item.getDescricao().length() > 255) {
            model.addAttribute("mensagem", "Descrição muito longa! Máximo 255 caracteres.");
            List<IngredienteModel> ingredientes = ingredienteService.listarTodos();
            model.addAttribute("ingredientesDisponiveis", ingredientes);
            return "Cardapio/adicionar";
        }

        if (result.hasErrors()) {
            List<IngredienteModel> ingredientes = ingredienteService.listarTodos();
            model.addAttribute("ingredientesDisponiveis", ingredientes);
            return "Cardapio/adicionar";
        }

        try {
            ItemModel itemSalvo = itemService.criarItem(item);

            if (itemSalvo == null) {
                model.addAttribute("mensagem", "Ocorreu um erro ao salvar o item!");
                List<IngredienteModel> ingredientes = ingredienteService.listarTodos();
                model.addAttribute("ingredientesDisponiveis", ingredientes);
                return "Cardapio/adicionar";
            }

            model.addAttribute("mensagem", "Item cadastrado com sucesso!");
            return "redirect:/fastfood/cardapio/listar";

        } catch (DataIntegrityViolationException e) {
            model.addAttribute("mensagem", "Erro: Dados excedem o limite permitido!");
            List<IngredienteModel> ingredientes = ingredienteService.listarTodos();
            model.addAttribute("ingredientesDisponiveis", ingredientes);
            return "Cardapio/adicionar";
        } catch (Exception e) {
            model.addAttribute("mensagem", "Erro ao criar item: " + e.getMessage());
            List<IngredienteModel> ingredientes = ingredienteService.listarTodos();
            model.addAttribute("ingredientesDisponiveis", ingredientes);
            return "Cardapio/adicionar";
        }
    }
}