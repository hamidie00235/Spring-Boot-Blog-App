package springBootBlogApi.com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springBootBlogApi.com.payLoad.LoginDto;
import springBootBlogApi.com.payLoad.RegisterDto;
import springBootBlogApi.com.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthContoller {
    private AuthService authService;

    public AuthContoller(AuthService authService) {
        this.authService = authService;
    }
    ///  Build login rest api

    @PostMapping(value = {"/login","/signin"})
    public ResponseEntity<String> login( @RequestBody LoginDto loginDto){
        String respond= authService.login(loginDto);
        return ResponseEntity.ok(respond);
    }
    // Build Register rest api
    @PostMapping(value = {"/register","/signup"})
    public ResponseEntity<String > register(@RequestBody RegisterDto registerDto){
        String respond = authService.register(registerDto);
        return new ResponseEntity<>(respond, HttpStatus.CREATED);

    }
}
