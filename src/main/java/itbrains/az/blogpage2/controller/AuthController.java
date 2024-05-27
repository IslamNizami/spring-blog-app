package itbrains.az.blogpage2.controller;


import itbrains.az.blogpage2.dtos.authdtos.RegisterDto;
import itbrains.az.blogpage2.services.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {


    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

    @GetMapping("/register")
    public String register()
    {
        return "register";
    }

    @PostMapping("/register")
    public String register(RegisterDto registerDto)
    {
        boolean res = userService.register(registerDto);
        if (res == false)
        {
            return "register";
        }
        return "redirect:/login";
    }

    @GetMapping("/auth/confirm")
    public String confirm(String email,String token)
    {
        userService.confirmEmail(email,token);
        return "redirect:/login";
    }
}
