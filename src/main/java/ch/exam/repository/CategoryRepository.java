package ch.exam.repository;

import ch.exam.model.entity.Category;
import ch.exam.model.entity.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findByCategory(CategoryName categoryName);
}
