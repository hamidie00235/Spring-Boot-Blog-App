package springBootBlogApi.com.service;

import springBootBlogApi.com.payLoad.LoginDto;
import springBootBlogApi.com.payLoad.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
