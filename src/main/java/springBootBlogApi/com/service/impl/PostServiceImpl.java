package springBootBlogApi.com.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import springBootBlogApi.com.entity.Post;
import springBootBlogApi.com.exception.ResourceNotFoundException;
import springBootBlogApi.com.payLoad.PostDto;
import springBootBlogApi.com.payLoad.PostResponse;
import springBootBlogApi.com.repository.PostRepository;
import springBootBlogApi.com.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {


    private PostRepository postRepository;
    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository,ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper=mapper;
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
    // pagination step 3
    public  PostResponse getAllPosts(int pageNo,int pageSize, String sortBy,String sortDir) {
       Sort sort =sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
               :Sort.by(sortBy).ascending();

        // create pageable instance step4,
   Pageable pageable = PageRequest.of(pageNo,pageSize, sort);
Page<Post> posts=postRepository.findAll(pageable);

// get content for page  object
        List<Post> listOfPosts=posts.getContent();



     List<PostDto>  content=listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

     PostResponse postResponse= new PostResponse();

     postResponse.setContent(content);
     postResponse.setPageNo(posts.getNumber());
     postResponse.setPageSizce(posts.getSize());
     postResponse.setTotalElements(posts.getTotalElements());
     postResponse.setTotalPages(posts.getTotalPages());
     postResponse.setLast(posts.isLast());
     return  postResponse;
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

    @Override
    public void deletePostById(Long id) {
        // get post by id from the database
        Post post =postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));
       postRepository.delete(post);
    }

    // convert Entity to Dto
    private PostDto mapToDto(Post post){

        PostDto postDto=mapper.map(post,PostDto.class);
//        PostDto postDto=new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());

        return postDto;
    }
    // convert  Dto to entity
    private Post mapToEntity(PostDto postDto){

        Post post =mapper.map(postDto,Post.class);


//        Post post =new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());

        return post;
    }
}
