package springBootBlogApi.com.service;

import springBootBlogApi.com.payLoad.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long postId,CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(Long postId);
}
