package springBootBlogApi.com.payLoad;

import lombok.Data;

@Data
public class PostDto {
    private String resourceName;
    private String fieldName;
    private String fieldValue;
}
