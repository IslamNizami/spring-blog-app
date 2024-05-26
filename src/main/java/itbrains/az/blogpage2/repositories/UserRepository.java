package itbrains.az.blogpage2.repositories;

import itbrains.az.blogpage2.dtos.userdtos.UserDashboardListDto;
import itbrains.az.blogpage2.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findByEmail(String email);
}
