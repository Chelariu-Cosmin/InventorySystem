package ro.beenear.onlineshop.inventoryservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.beenear.onlineshop.inventoryservice.entities.Lot;
import java.util.List;

public interface InventoryRepository extends JpaRepository<Lot, Long> {

    List<Lot> findAllLotsByArticleId(Long articleId);
}
