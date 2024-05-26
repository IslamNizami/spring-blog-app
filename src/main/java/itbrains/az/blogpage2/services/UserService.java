package itbrains.az.blogpage2.services;

import itbrains.az.blogpage2.dtos.authdtos.RegisterDto;
import itbrains.az.blogpage2.dtos.userdtos.UserDashboardListDto;

import java.util.List;

public interface UserService {
     boolean register(RegisterDto register);
     boolean confirmEmail(String email, String token);
     List<UserDashboardListDto> getDashboardUsers();

}
