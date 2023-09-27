package springBootBlogApi.com.service;

import springBootBlogApi.com.payLoad.CommentDto;

public interface CommentService {
    CommentDto createComment(Long postId,CommentDto commentDto);
}
