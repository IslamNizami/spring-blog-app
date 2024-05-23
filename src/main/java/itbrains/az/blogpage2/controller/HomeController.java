package itbrains.az.blogpage2.controller;


import itbrains.az.blogpage2.dtos.articledtos.ArticleHomeDto;
import itbrains.az.blogpage2.dtos.categorydtos.CategoryCreateDto;
import itbrains.az.blogpage2.services.ArticleService;
import itbrains.az.blogpage2.services.CategoryService;
import itbrains.az.blogpage2.services.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public String index(Model model) {
        List<ArticleHomeDto> homeArticles = articleService.getHomeArticles();
        model.addAttribute("articles",homeArticles);
        return "home";
    }

}
