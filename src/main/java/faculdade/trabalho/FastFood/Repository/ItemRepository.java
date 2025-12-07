package faculdade.trabalho.FastFood.Repository;

import faculdade.trabalho.FastFood.Model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemModel, Long> {
    Optional<ItemModel> findByNomeIgnoreCase(String nome);
}