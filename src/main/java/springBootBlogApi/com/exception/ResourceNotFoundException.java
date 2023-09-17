package springBootBlogApi.com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String FieldName;
    private String fieldValue;

    public ResourceNotFoundException(String resourceName, String FieldName, String fieldValue) {

        super(String.format("%s Not found with %s:'%s' ",resourceName,FieldName,fieldValue));    //// Post not Found with id;
        this.resourceName = resourceName;
        this.FieldName = FieldName;
        this.fieldValue =fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return FieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }
}
