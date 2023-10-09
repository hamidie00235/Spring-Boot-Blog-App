package springBootBlogApi.com.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import springBootBlogApi.com.payLoad.LoginDto;
import springBootBlogApi.com.service.AuthService;
@Service
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;

    public AuthServiceImpl(AuthenticationManager authenticationManager){
        this.authenticationManager=authenticationManager;

    }

    @Override
    public String login(LoginDto loginDto) {
      Authentication authentication= authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(loginDto.getUserNameOrEmail(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ("User logged-in successfully!.");
    }
}
