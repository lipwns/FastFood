package faculdade.trabalho.FastFood.Repository;

import faculdade.trabalho.FastFood.Model.ItemIngredienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemIngredienteRepository extends JpaRepository<ItemIngredienteModel, Long> {
}