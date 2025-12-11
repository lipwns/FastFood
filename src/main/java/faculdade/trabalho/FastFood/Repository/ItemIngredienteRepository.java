package faculdade.trabalho.FastFood.Repository;

import faculdade.trabalho.FastFood.Model.IngredienteModel;
import faculdade.trabalho.FastFood.Model.ItemIngredienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemIngredienteRepository extends JpaRepository<ItemIngredienteModel, Long> {
    List<ItemIngredienteModel> findByIngrediente(IngredienteModel ingrediente);
}