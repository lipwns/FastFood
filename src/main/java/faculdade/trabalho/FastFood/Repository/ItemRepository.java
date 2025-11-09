package faculdade.trabalho.FastFood.Repository;

import faculdade.trabalho.FastFood.Model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemModel, Long> {


}