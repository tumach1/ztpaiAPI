package com.ztpai.api.web.controllers;

import com.ztpai.api.Mappers.UserMapper;
import com.ztpai.api.dao.FollowingsDao;
import com.ztpai.api.dao.UserDao;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import com.ztpai.api.services.UserService;
import com.ztpai.api.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "*")
    @GetMapping("/users/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        System.out.println("Fetching user with ID: " + username);
        UserDao userDao = userService.findByUsername(username);
        System.out.println(userDao.getAuthorities().stream().map(
                authority -> authority.getAuthority()).toList());;

        if (userDao != null) {
            return ResponseEntity.ok(UserMapper.toDto(userDao, false));
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
        createdUserDto = UserMapper.toDto(createdUser, false);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        userDto.setId(id);
        UserDao updatedUser = userService.save(userDto);
        if (updatedUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        UserDto updatedUserDto = UserMapper.toDto(updatedUser, false);
        return ResponseEntity.ok(updatedUserDto);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/users/getFollowers/username/{username}")
    public ResponseEntity<List<UserDto>> getFollowers(@PathVariable String username) {
        UserDao user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Set<FollowingsDao> followers = user.getFollowers();
        if (followers == null || followers.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }
        List<UserDto> followersDtos = followers.stream()
                .map(following -> UserMapper.toDto(following.getFollower(), false)).toList();
        return ResponseEntity.ok(followersDtos);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/users/getFollowings/username/{username}")
    public ResponseEntity<List<UserDto>> getFollowings(@PathVariable String username) {
        UserDao user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Set<FollowingsDao> followings = user.getFollowings();
        if (followings == null || followings.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }
        List<UserDto> followingsDtos = followings.stream()
                .map(following -> UserMapper.toDto(following.getCreator(), false)).toList();
        return ResponseEntity.ok(followingsDtos);
    }


}
