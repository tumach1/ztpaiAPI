package com.ztpai.api.web.controllers;


import com.ztpai.api.Mappers.UserMapper;
import com.ztpai.api.Responses.LoginResponse;
import com.ztpai.api.dao.UserDao;
import com.ztpai.api.dto.AuthRequest;
import com.ztpai.api.dto.UserDto;
import com.ztpai.api.exceptions.*;
import com.ztpai.api.repositories.UserRepository;
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
    public ResponseEntity<String> addNewUser(@RequestBody UserDto user) {
        try{
        service.save(user);}
        catch (Exception e) {
            if (e.getCause() != null && e.getCause().getMessage().contains("username")) {
                throw new UsernameAlreadyExistException("Username already exists: " + user.getUsername());
            } else if (e.getCause() != null && e.getCause().getMessage().contains("email")) {
                throw new EmailAlreadyExistException("Email already exists: " + user.getEmail());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while saving user: " + e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        if(!service.existsByUsername(authRequest.getUsername())) {
            System.out.println("User not found with username: " + authRequest.getUsername());
            throw new InvalidUsernameException("User not found with username: " + authRequest.getUsername());
        }

        System.out.println("Here");
        try{
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                LoginResponse loginResponse = new LoginResponse(
                        jwtService.generateToken(authRequest.getUsername()),
                        authRequest.getUsername()
                );


                return ResponseEntity.ok(loginResponse);
            }}
        catch (UsernameNotFoundException e) {
            System.out.println("Invalid username: " + authRequest.getUsername());
            throw new InvalidUsernameException("Invalid username: " + authRequest.getUsername());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Invalid password for user: " + authRequest.getUsername());
            throw new InvalidPasswordException("Invalid password for user: " + authRequest.getUsername());
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(InvalidUsernameException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleInvalidUsernameException(InvalidUsernameException e) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleInvalidPasswordException(InvalidPasswordException e) {
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }

}
