package itbrains.az.blogpage2.repositories;

import itbrains.az.blogpage2.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long> {
}
