package springBootBlogApi.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springBootBlogApi.com.entity.Post;

public interface PostRepository extends JpaRepository<Post,Long> {
}
