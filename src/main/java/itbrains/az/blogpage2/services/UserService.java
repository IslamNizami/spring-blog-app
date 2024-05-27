package itbrains.az.blogpage2.services;

import itbrains.az.blogpage2.dtos.authdtos.RegisterDto;
import itbrains.az.blogpage2.dtos.userdtos.UserAddRoleDto;
import itbrains.az.blogpage2.dtos.userdtos.UserDashboardListDto;
import itbrains.az.blogpage2.dtos.userdtos.UserDto;

import java.util.List;

public interface UserService {
     boolean register(RegisterDto register);
     boolean confirmEmail(String email, String token);
     List<UserDashboardListDto> getDashboardUsers();
     UserDto getUserById(Long id);
     void addRole(UserAddRoleDto userAddRole);
}
