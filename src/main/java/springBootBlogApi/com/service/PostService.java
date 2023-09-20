package springBootBlogApi.com.service;

import springBootBlogApi.com.payLoad.PostDto;

import java.util.List;

public interface PostService {
 PostDto createPost(PostDto postDto);
 List<PostDto> getAllPosts();
 PostDto getPostById(Long id);
}
