package itbrains.az.blogpage2.services;

import itbrains.az.blogpage2.dtos.authdtos.RegisterDto;

public interface UserService {
     boolean register(RegisterDto register);
     boolean confirmEmail(String email, String token);
}
