package in.tiepdhm.billingsoftware.repository;

import in.tiepdhm.billingsoftware.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository <CategoryEntity, Long>{

    Optional<CategoryEntity> findByCategoryId(String categoryId);
}
