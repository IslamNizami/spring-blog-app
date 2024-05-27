package itbrains.az.blogpage2.repositories;

import itbrains.az.blogpage2.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
