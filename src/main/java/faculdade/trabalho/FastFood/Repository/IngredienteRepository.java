package faculdade.trabalho.FastFood.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import faculdade.trabalho.FastFood.Model.IngredienteModel;
import java.util.Optional;

@Repository
public interface IngredienteRepository extends JpaRepository<IngredienteModel, Long> {
    Optional<IngredienteModel> findByNome(String nome);

}