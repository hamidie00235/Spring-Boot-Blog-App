package springBootBlogApi.com.service;

import springBootBlogApi.com.payLoad.PostDto;
import springBootBlogApi.com.payLoad.PostResponse;

import java.util.List;

public interface PostService {
 PostDto createPost(PostDto postDto);
 //pagination step 2
 PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir);
 PostDto getPostById(Long id);
 PostDto updatePost( PostDto postDto ,Long id);
 void deletePostById(Long id);
}
