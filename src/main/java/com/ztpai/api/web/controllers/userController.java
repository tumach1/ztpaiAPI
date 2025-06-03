package com.ztpai.api.web.controllers;

import com.ztpai.api.Mappers.UserMapper;
import com.ztpai.api.dao.UserDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import com.ztpai.api.services.UserService;
import com.ztpai.api.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@RestController
public class userController {
    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "*")
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        System.out.println("Fetching user with ID: " + id);
        UserDto user = userService.findById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        System.out.println("Creating user: " + userDto.getUsername());
        UserDao createdUser = userService.save(userDto);
        if (createdUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        UserDto createdUserDto;
        createdUserDto = UserMapper.toDto(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
    }

//    @CrossOrigin(origins = "*")
//    @PutMapping("/users/{id}")
//    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
//        userDto.setId(id);
//        UserDto updatedUser = userService.save(userDto);
//        return ResponseEntity.ok(updatedUser);
//    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
