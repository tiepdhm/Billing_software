package in.tiepdhm.billingsoftware.repository;

import in.tiepdhm.billingsoftware.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemEntity,Long> {

    Optional<ItemEntity> findByItemId(String itemId);

    Integer countByCategoryId(Long id);
}
