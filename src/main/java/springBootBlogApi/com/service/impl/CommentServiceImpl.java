package springBootBlogApi.com.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import springBootBlogApi.com.entity.Comment;
import springBootBlogApi.com.entity.Post;
import springBootBlogApi.com.exception.BlogAPIException;
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

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {

        //retrieve post entity by id from db

        Post post =postRepository.findById(postId).
                orElseThrow(()->new ResourceNotFoundException("post","id",postId));
        //retrieve comment entity by id from db

         Comment comment=commentRepository.findById(commentId).orElseThrow(()->
        new ResourceNotFoundException("comment","id",commentId));

         if(!comment.getPost().getId().equals(post.getId())){
             throw new BlogAPIException(HttpStatus.BAD_REQUEST,"comment does not belong to post");

         }
        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest) {
        //retrieve post entity by id from db

        Post post =postRepository.findById(postId).
                orElseThrow(()->new ResourceNotFoundException("post","id",postId));
        //retrieve comment entity by id from db

        Comment comment=commentRepository.findById(commentId).orElseThrow(()->
                new ResourceNotFoundException("comment","id",commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"comment does not belong to post");

        }
        comment.setId(commentRequest.getId());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        Comment updatedComment=commentRepository.save(comment);

        return mapToDto(updatedComment);


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
