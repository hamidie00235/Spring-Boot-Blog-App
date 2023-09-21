package springBootBlogApi.com.service;

import springBootBlogApi.com.payLoad.PostDto;

import java.util.List;

public interface PostService {
 PostDto createPost(PostDto postDto);
 //pagination step 2
 List<PostDto> getAllPosts( int pageNo,int pageSize);
 PostDto getPostById(Long id);
 PostDto updatePost( PostDto postDto ,Long id);
 void deletePostById(Long id);
}
