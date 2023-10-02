package springBootBlogApi.com.payLoad;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private Long id;
    // title should not be null or empty
    //title should have at least 2 characters
  @NotEmpty
  @Size(min=2 ,message = "title should have at least 2 characters")
    private String title;
    // post description should not be null or empty
    //post description should have at least 10 characters
    @NotEmpty
    @Size(min = 10,message = "post description should have at least 10 characters")
    private String description;
    //content should not be nul or empty
    @NotEmpty

    private String content;
    private Set<CommentDto> comments;
}
