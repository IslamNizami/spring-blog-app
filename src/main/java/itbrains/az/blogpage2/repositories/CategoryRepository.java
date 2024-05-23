package itbrains.az.blogpage2.repositories;

import itbrains.az.blogpage2.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CategoryRepository extends JpaRepository<Category,Long> {
}
