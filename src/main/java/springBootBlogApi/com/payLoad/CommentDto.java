package springBootBlogApi.com.payLoad;

import lombok.Data;

@Data
public class CommentDto {

    private Long id;
    private String name;
    private String email;
    private String body;
}
