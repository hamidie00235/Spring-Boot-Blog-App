package springBootBlogApi.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springBootBlogApi.com.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findAllByEmail(String email);
    Optional<User> findAllByUserNameOrEmail(String userName,String email);

    Optional<User> findAllByUserName(String userName);

    Boolean existsByUserName(String userName);

    Boolean existsByEmail(String email);
}
