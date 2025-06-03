package com.ztpai.api.Mappers;

import com.ztpai.api.dao.FollowingsDao;
import com.ztpai.api.dao.UserDao;
import com.ztpai.api.dto.UserDto;

import java.util.Set;

public class UserMapper {
    public static UserDto toDto(UserDao user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
//        Set<UserDto> followers = user.getFollowers();
        for (FollowingsDao follower : user.getFollowers()) {
            UserDto followerDto = new UserDto();
            followerDto.setId(follower.getFollower().getId());
            followerDto.setUsername(follower.getFollower().getUsername());
            followerDto.setEmail(follower.getFollower().getEmail());
            userDto.getFollowers().add(followerDto);
            System.out.println(followerDto);
        }
        return userDto;
    }

    public static UserDao toDao(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        UserDao user = new UserDao();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        return user;
    }

}
