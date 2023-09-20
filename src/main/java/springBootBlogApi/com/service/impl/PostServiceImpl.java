package springBootBlogApi.com.service.impl;

import org.springframework.stereotype.Service;
import springBootBlogApi.com.entity.Post;
import springBootBlogApi.com.exception.ResourceNotFoundException;
import springBootBlogApi.com.payLoad.PostDto;
import springBootBlogApi.com.repository.PostRepository;
import springBootBlogApi.com.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {


    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        // convert Dto to entity
        Post post= mapToEntity(postDto);

        Post newPost=postRepository.save(post);
        //convert entity to Dto
        PostDto postResponse = mapToDto(newPost);



        return postResponse;
    }

    @Override
    public List<PostDto> getAllPosts() {
 List<Post> posts=postRepository.findAll();
        return posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post =postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        // get post by id from the database
        Post post =postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatePost=postRepository.save(post);
        return mapToDto(updatePost);
    }

    // convert Entity to Dto
    private PostDto mapToDto(Post post){
        PostDto postDto=new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());

        return postDto;
    }
    private Post mapToEntity(PostDto postDto){
        Post post =new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
}
