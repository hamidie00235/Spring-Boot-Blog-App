package springBootBlogApi.com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springBootBlogApi.com.payLoad.PostDto;
import springBootBlogApi.com.payLoad.PostResponse;
import springBootBlogApi.com.service.PostService;
import springBootBlogApi.com.utils.AppConstants;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    // create blog post
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
    // get all posts and pagination step1
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value ="pageNo" ,defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value="pageSize" ,defaultValue = AppConstants.DEFAULT_PAGE_SIZE ,required = false) int pageSize,
            @RequestParam(value="sortBy",defaultValue = AppConstants.DEFAULT_PAGE_SORT_BY,required = false) String sortBy,
            @RequestParam(value="sortDir",defaultValue=AppConstants.DEFAULT_PAGE_SORT_DIRECTION,required=false) String sortDir
    ){
        return postService.getAllPosts( pageNo ,pageSize,sortBy,sortDir);
    }
    // Get Post by id
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }
    //update post by id rest api
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost (@RequestBody PostDto postDto, @PathVariable(name="id")Long id){
        PostDto postResponse=postService.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }
    // Delete post by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable(name="id") Long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("post entity deleted successfully",HttpStatus.OK);

    }

}
