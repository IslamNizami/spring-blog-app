package itbrains.az.blogpage2.repositories;

import itbrains.az.blogpage2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
}
