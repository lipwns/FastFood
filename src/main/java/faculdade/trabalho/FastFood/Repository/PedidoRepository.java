package faculdade.trabalho.FastFood.Repository;

import faculdade.trabalho.FastFood.Model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {
}