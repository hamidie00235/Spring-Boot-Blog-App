package springBootBlogApi.com.service.impl;

import org.springframework.stereotype.Service;
import springBootBlogApi.com.entity.Comment;
import springBootBlogApi.com.entity.Post;
import springBootBlogApi.com.exception.ResourceNotFoundException;
import springBootBlogApi.com.payLoad.CommentDto;
import springBootBlogApi.com.repository.CommentRepository;
import springBootBlogApi.com.repository.PostRepository;
import springBootBlogApi.com.service.CommentService;

import java.rmi.NotBoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository=postRepository;
    }

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {

        Comment comment=mapToEntity(commentDto);

        //retrieve post entity by id

        Post post =postRepository.findById(postId).
                orElseThrow(()->new ResourceNotFoundException("post","id",postId));

        // Set post to comment entity
        comment.setPost(post);

        //comment entity to db
        Comment newComment=commentRepository.save(comment);

        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        // Retrieve comments by postId

        List<Comment> comments=commentRepository.findByPostId(postId);
      // convet list of comment entities to list of comment dto's
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    // convert Entity to Dto
    private CommentDto mapToDto(Comment comment){

        CommentDto commentDto=new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
return commentDto;

    }
    //convert Dto to Entity
    private Comment mapToEntity(CommentDto commentDto){
        Comment comment =new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return  comment;
    }
}
