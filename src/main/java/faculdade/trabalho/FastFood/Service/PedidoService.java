package faculdade.trabalho.FastFood.Service;

import faculdade.trabalho.FastFood.Model.PedidoModel;
import faculdade.trabalho.FastFood.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    // Salvar ou atualizar pedido
    public PedidoModel salvar(PedidoModel pedido) {
        return pedidoRepository.save(pedido);
    }

    // Listar todos os pedidos
    public List<PedidoModel> listarTodos() {
        return pedidoRepository.findAll();
    }

    // Buscar pedido por ID
    public Optional<PedidoModel> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    // Excluir pedido por ID
    public void excluir(Long id) {
        pedidoRepository.deleteById(id);
    }
}