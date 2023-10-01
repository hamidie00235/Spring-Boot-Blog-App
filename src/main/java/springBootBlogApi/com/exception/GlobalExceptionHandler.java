package springBootBlogApi.com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import springBootBlogApi.com.payLoad.ErrorDetails;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {



    //Handle specific exception
    //1 ResourceNotFoundException
@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
                   ErrorDetails errorDetails=new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));


 return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    //2 BlogAPIException
    @ExceptionHandler(BlogAPIException.class)
    public ResponseEntity<ErrorDetails> handleBlogAPIExceptionException(BlogAPIException exception, WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));


        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    //Handle global exception
}
