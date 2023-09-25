package springBootBlogApi.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springBootBlogApi.com.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {


}
