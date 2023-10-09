package springBootBlogApi.com.service;

import springBootBlogApi.com.payLoad.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}
