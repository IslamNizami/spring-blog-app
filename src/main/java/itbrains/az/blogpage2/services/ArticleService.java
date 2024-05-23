package itbrains.az.blogpage2.services;

import itbrains.az.blogpage2.dtos.articledtos.*;

import java.util.List;

public interface ArticleService {
    List<ArticleDto> getArticles();
    void addArticle(ArticleCreateDto articleCreateDto);
    List<ArticleHomeDto> getHomeArticles();
    void removeArticle(Long articleId);
    void updateArticle(ArticleUpdateDto articleUpdateDto);
    ArticleUpdateDto findUpdateArticle(Long id);

    ArticleDetailDto articleDetail(Long id);

}
