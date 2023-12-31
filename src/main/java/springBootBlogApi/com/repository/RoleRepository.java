package springBootBlogApi.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springBootBlogApi.com.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role>findByName(String name);
}
