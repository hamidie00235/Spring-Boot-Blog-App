package springBootBlogApi.com.service.impl;

import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springBootBlogApi.com.entity.Role;
import springBootBlogApi.com.entity.User;
import springBootBlogApi.com.exception.BlogAPIException;
import springBootBlogApi.com.payLoad.LoginDto;
import springBootBlogApi.com.payLoad.RegisterDto;
import springBootBlogApi.com.repository.RoleRepository;
import springBootBlogApi.com.repository.UserRepository;
import springBootBlogApi.com.service.AuthService;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager,UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder){

        this.authenticationManager=authenticationManager;
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
        this.passwordEncoder=passwordEncoder;

    }

    @Override
    public String login(LoginDto loginDto) {
      Authentication authentication= authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(loginDto.getUserNameOrEmail(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ("User logged-in successfully!.");
    }

    @Override
    public String register(RegisterDto registerDto) {

        // add check for username exists in database
        if(userRepository.existsByUserName(registerDto.getUsername())){
            throw  new BlogAPIException(HttpStatus.BAD_REQUEST,"the username is already exists ");
        }
        // add check for email exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw  new BlogAPIException(HttpStatus.BAD_REQUEST,"the email   is already exists ");
        }
        User user=new User();
        user.setName(registerDto.getName());
        user.setUserName(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Set<Role> roles =new HashSet<>();
        Role userRole=roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);

        return "User registered successfully!";
    }
}
