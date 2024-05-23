package itbrains.az.blogpage2.services.impl;


import itbrains.az.blogpage2.dtos.articledtos.*;
import itbrains.az.blogpage2.dtos.categorydtos.CategoryCreateDto;
import itbrains.az.blogpage2.helpers.SeoHelper;
import itbrains.az.blogpage2.models.Article;
import itbrains.az.blogpage2.models.Category;
import itbrains.az.blogpage2.repositories.ArticleRepository;
import itbrains.az.blogpage2.repositories.CategoryRepository;
import itbrains.az.blogpage2.services.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<ArticleDto> getArticles() {
        List<ArticleDto> articles = articleRepository.findAll().stream()
                .filter(article -> article.getIsDeleted() == false)
                .map(article -> modelMapper.map(article, ArticleDto.class))
                .collect(Collectors.toList());
        return articles;
    }

    @Override
    public void addArticle(ArticleCreateDto articleCreateDto) {
        try {
            Article article = new Article();
            article.setUpdatedDate(new Date());
            article.setCreatedDate(new Date());
            article.setTitle(articleCreateDto.getTitle());
            SeoHelper seoHelper = new SeoHelper();
            article.setSeoUrl(seoHelper.seoUrlHelper(articleCreateDto.getTitle()));
            article.setSubTitle(articleCreateDto.getSubTitle());
            article.setDescription(articleCreateDto.getDescription());
            article.setPhotoUrl(articleCreateDto.getPhotoUrl());
            Category category = categoryRepository.findById(articleCreateDto.getCategoryId()).get();
            article.setCategory(category);
            articleRepository.save(article);
        }catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<ArticleHomeDto> getHomeArticles() {
        List<ArticleHomeDto> articles = articleRepository.findAll().stream()
                .filter(article -> article.getIsDeleted() == false)
                .map(article -> modelMapper.map(article, ArticleHomeDto.class))
                .collect(Collectors.toList());
        return articles;
    }

    @Override
    public void removeArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow();
        article.setIsDeleted(true);
        articleRepository.save(article);
    }

    @Override
    public void updateArticle(ArticleUpdateDto articleUpdateDto) {
        Article findArticle = articleRepository.findById(articleUpdateDto.getId()).orElseThrow();
        Category findCategory = categoryRepository.findById(articleUpdateDto.getCategoryId()).orElseThrow();
        findArticle.setTitle(articleUpdateDto.getTitle());
        findArticle.setSubTitle(articleUpdateDto.getSubTitle());
        findArticle.setUpdatedDate(new Date());
        findArticle.setDescription(articleUpdateDto.getDescription());
        findArticle.setPhotoUrl(articleUpdateDto.getPhotoUrl());
        findArticle.setCategory(findCategory);
        SeoHelper seoHelper = new SeoHelper();
        findArticle.setSeoUrl(seoHelper.seoUrlHelper(articleUpdateDto.getTitle()));
        articleRepository.saveAndFlush(findArticle);

    }

    @Override
    public ArticleUpdateDto findUpdateArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow();
        ArticleUpdateDto articleUpdateDto = modelMapper.map(article,ArticleUpdateDto.class);
        return articleUpdateDto;
    }

    @Override
    public ArticleDetailDto articleDetail(Long id) {
        Article article = articleRepository.findById(id).orElseThrow();
        ArticleDetailDto articleDetailDto = modelMapper.map(article,ArticleDetailDto.class);
        return articleDetailDto;
    }
}
