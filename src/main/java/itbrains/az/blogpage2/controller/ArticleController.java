package itbrains.az.blogpage2.controller;


import groovy.transform.AutoImplement;
import itbrains.az.blogpage2.dtos.articledtos.ArticleDetailDto;
import itbrains.az.blogpage2.repositories.ArticleRepository;
import itbrains.az.blogpage2.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("detail/{id}/{seoUrl}")
    public String detail(@PathVariable Long id, Model model)
    {
        ArticleDetailDto articleDetailDto = articleService.articleDetail(id);
        model.addAttribute("article", articleDetailDto);
        return "detail";
    }
}
