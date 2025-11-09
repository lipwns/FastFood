package faculdade.trabalho.FastFood.Repository;

import faculdade.trabalho.FastFood.Model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {

}