package itbrains.az.blogpage2.controller;


import itbrains.az.blogpage2.dtos.articledtos.ArticleCreateDto;
import itbrains.az.blogpage2.dtos.articledtos.ArticleDto;
import itbrains.az.blogpage2.dtos.articledtos.ArticleUpdateDto;
import itbrains.az.blogpage2.dtos.categorydtos.CategoryCreateDto;
import itbrains.az.blogpage2.dtos.categorydtos.CategoryDto;
import itbrains.az.blogpage2.dtos.roledtos.RoleDto;
import itbrains.az.blogpage2.dtos.userdtos.UserAddRoleDto;
import itbrains.az.blogpage2.dtos.userdtos.UserDashboardListDto;
import itbrains.az.blogpage2.dtos.userdtos.UserDto;
import itbrains.az.blogpage2.models.Article;
import itbrains.az.blogpage2.models.Category;
import itbrains.az.blogpage2.repositories.RoleRepository;
import itbrains.az.blogpage2.repositories.UserRepository;
import itbrains.az.blogpage2.services.ArticleService;
import itbrains.az.blogpage2.services.CategoryService;
import itbrains.az.blogpage2.services.RoleService;
import itbrains.az.blogpage2.services.UserService;
import itbrains.az.blogpage2.services.impl.RoleServiceImpl;
import itbrains.az.blogpage2.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/admin")
    public String index()
    {
        return "/dashboard/home";
    }

    @GetMapping("/admin/category")
    public String category(Model model)
    {
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories",categories);
        return "/dashboard/category";
    }

    @GetMapping("/admin/category/category-create")
    public String addCategory()
    {
        return "/dashboard/category-create";
    }

    @PostMapping("/admin/category/create")
    public String addCategory(@ModelAttribute CategoryCreateDto categoryCreateDto)
    {
        categoryService.add(categoryCreateDto);
        return "redirect:/admin/category";
    }

    @GetMapping("/admin/article")
    public String createArticle(Model model)
    {
        List<ArticleDto> articles = articleService.getArticles();
        model.addAttribute("articles",articles);
        return "/dashboard/article";
    }

    @GetMapping("/admin/article/create")
    public String addArticle(Model model)
    {
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories",categories);
        return "/dashboard/article-create";
    }

    @PostMapping("/admin/article/create")
    public String articleCreate(@ModelAttribute ArticleCreateDto articleCreateDto)
    {
        articleService.addArticle(articleCreateDto);
        return "redirect:/admin/article";
    }

    @GetMapping("/admin/article/remove/{id}")
    public String removeArticle(@ModelAttribute @PathVariable Long id)
    {
        articleService.removeArticle(id);
        return "redirect:/admin/article";
    }

    @GetMapping("/admin/article/update/{id}")
    public String updateArticle(@ModelAttribute @PathVariable Long id,Model model)
    {
        ArticleUpdateDto articleUpdateDto = articleService.findUpdateArticle(id);
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("article", articleUpdateDto);
        return "dashboard/article/update";
    }

    @PostMapping("/admin/article/update")
    public String updateArticle(@ModelAttribute ArticleUpdateDto articleUpdateDto)
    {
        articleService.updateArticle(articleUpdateDto);
        return "redirect:/admin/article";
    }

    @GetMapping("/admin/users")
    public String getUsers(Model model)
    {
        List<UserDashboardListDto> users = userService.getDashboardUsers();
        model.addAttribute("users",users);
        return "/dashboard/auth/user-list";
    }

    @GetMapping("/admin/users/role/{id}")
    public String addRole(@PathVariable Long id, Model model)
    {
        List<RoleDto> roles = roleService.getRoles();
        model.addAttribute("roles",roles);
        UserDto user = userService.getUserById(id);
        model.addAttribute("user",user);
        return "/dashboard/auth/user-role";
    }

    @PostMapping("/admin/users/addrole")
    public String addRole(UserAddRoleDto addRoleDto)
    {
        userService.addRole(addRoleDto);
        return "redirect:/admin/users";
    }
}
