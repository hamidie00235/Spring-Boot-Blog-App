package springBootBlogApi.com.service.impl;

import org.springframework.stereotype.Service;
import springBootBlogApi.com.entity.Post;
import springBootBlogApi.com.payLoad.PostDto;
import springBootBlogApi.com.repository.PostRepository;
import springBootBlogApi.com.service.PostService;
@Service
public class PostServiceImpl implements PostService {


    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        // convert Dto to entity
        Post post =new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post newPost=postRepository.save(post);
        //convert entity to Dto
        PostDto postResponse =new PostDto();
        postResponse.setId(newPost.getId());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setContent(newPost.getContent());
        return postResponse;
    }
}
