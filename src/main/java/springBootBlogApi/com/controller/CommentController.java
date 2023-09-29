package springBootBlogApi.com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springBootBlogApi.com.entity.Comment;
import springBootBlogApi.com.payLoad.CommentDto;
import springBootBlogApi.com.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    /// create commentdto method
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value="postId") Long postId ,
                                                    @RequestBody CommentDto  commentDto){

     return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }
    // Get all comments by post Id rest api
@GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") Long postId){

       return commentService.getCommentsByPostId(postId);
    }
}
