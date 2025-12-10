package faculdade.trabalho.FastFood.Controller.UI;

import faculdade.trabalho.FastFood.Model.PedidoModel;
import faculdade.trabalho.FastFood.Model.ItemModel;
import faculdade.trabalho.FastFood.Service.PedidoService;
import faculdade.trabalho.FastFood.Service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/fastfood/pedidos")
@RequiredArgsConstructor
public class PedidoControllerUI {
    private final PedidoService pedidoService;
    private final ItemService itemService;

    @GetMapping("/listar")
    public String listarPedidos(Model model) {
        List<PedidoModel> pedidos = pedidoService.listarTodos();
        model.addAttribute("pedidos", pedidos);
        return "Pedidos/listar";
    }

    @GetMapping("/cancelar/{id}")
    public String cancelarPedido(@PathVariable Long id, Model model) {
        try {
            pedidoService.cancelarPedido(id);
            model.addAttribute("mensagem", "Pedido cancelado com sucesso!");
        } catch (Exception e) {
            model.addAttribute("mensagem", "Erro ao cancelar pedido: " + e.getMessage());
        }
        return "redirect:/fastfood/pedidos/listar";
    }

    @GetMapping("/avancar/{id}")
    public String avancarStatus(@PathVariable Long id, Model model) {
        Optional<PedidoModel> opPedido = pedidoService.buscarPorId(id);

        if (opPedido.isPresent()) {
            try {
                pedidoService.avancarStatus(opPedido.get());
                model.addAttribute("mensagem", "Status atualizado com sucesso!");
            } catch (Exception e) {
                model.addAttribute("mensagem", "Erro ao avançar status: " + e.getMessage());
            }
        } else {
            model.addAttribute("mensagem", "Pedido não encontrado!");
        }

        return "redirect:/fastfood/pedidos/listar";
    }

    @GetMapping("/voltar/{id}")
    public String voltarStatus(@PathVariable Long id, Model model) {
        Optional<PedidoModel> opPedido = pedidoService.buscarPorId(id);

        if (opPedido.isPresent()) {
            try {
                pedidoService.voltarStatus(opPedido.get());
                model.addAttribute("mensagem", "Status revertido com sucesso!");
            } catch (Exception e) {
                model.addAttribute("mensagem", "Erro ao voltar status: " + e.getMessage());
            }
        } else {
            model.addAttribute("mensagem", "Pedido não encontrado!");
        }

        return "redirect:/fastfood/pedidos/listar";
    }

    @GetMapping("/visualizar/{id}")
    public String visualizarPedido(@PathVariable Long id, Model model) {
        Optional<PedidoModel> opPedido = pedidoService.buscarPorId(id);

        if (opPedido.isPresent()) {
            model.addAttribute("pedido", opPedido.get());
            return "Pedidos/visualizar";
        }

        return "redirect:/fastfood/pedidos/listar";
    }

    @GetMapping("/criar")
    public String mostrarFormularioCriar(Model model) {
        model.addAttribute("pedido", new PedidoModel());

        List<ItemModel> itensDisponiveis = itemService.listarTodos();
        model.addAttribute("itensDisponiveis", itensDisponiveis);

        return "Pedidos/adicionar";
    }

    @PostMapping("/salvar")
    public String salvarPedido(@ModelAttribute("pedido") PedidoModel pedido,
                               BindingResult result,
                               Model model) {

        if (result.hasErrors()) {
            List<ItemModel> itensDisponiveis = itemService.listarTodos();
            model.addAttribute("itensDisponiveis", itensDisponiveis);
            return "Pedidos/adicionar";
        }

        try {
            PedidoModel pedidoSalvo = pedidoService.criarPedido(pedido);

            if (pedidoSalvo == null) {
                model.addAttribute("mensagem", "Erro ao criar pedido! Verifique o estoque de ingredientes.");
                List<ItemModel> itensDisponiveis = itemService.listarTodos();
                model.addAttribute("itensDisponiveis", itensDisponiveis);
                return "Pedidos/adicionar";
            }

            model.addAttribute("mensagem", "Pedido criado com sucesso!");
            return "redirect:/fastfood/pedidos/listar";

        } catch (DataIntegrityViolationException e) {
            model.addAttribute("mensagem", "Erro: Dados excedem o limite permitido!");
            List<ItemModel> itensDisponiveis = itemService.listarTodos();
            model.addAttribute("itensDisponiveis", itensDisponiveis);
            return "Pedidos/adicionar";
        } catch (Exception e) {
            model.addAttribute("mensagem", "Erro ao criar pedido: " + e.getMessage());
            List<ItemModel> itensDisponiveis = itemService.listarTodos();
            model.addAttribute("itensDisponiveis", itensDisponiveis);
            return "Pedidos/adicionar";
        }
    }
}