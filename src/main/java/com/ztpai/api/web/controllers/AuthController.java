package com.ztpai.api.web.controllers;


import com.ztpai.api.Mappers.UserMapper;
import com.ztpai.api.Responses.LoginResponse;
import com.ztpai.api.dao.UserDao;
import com.ztpai.api.dto.AuthRequest;
import com.ztpai.api.dto.UserDto;
import com.ztpai.api.services.JwtService;
import com.ztpai.api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserDto user) {
        return service.save(user).getUsername();
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        if (authentication.isAuthenticated()) {
            LoginResponse loginResponse = new LoginResponse(
                    jwtService.generateToken(authRequest.getUsername()),
                    authRequest.getUsername()
            );

            return ResponseEntity.ok(loginResponse);
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

}
