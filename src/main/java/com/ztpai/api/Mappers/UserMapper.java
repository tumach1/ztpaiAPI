package com.ztpai.api.Mappers;

import com.ztpai.api.dao.UserDao;
import com.ztpai.api.dto.UserDto;

import java.util.Set;

public class UserMapper {
    public static UserDto toDto(UserDao user, boolean isEmailSensitive) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        if (isEmailSensitive) {
            userDto.setEmail(user.getEmail());
        } else {
            userDto.setEmail(null);
        }
        userDto.setEmail(user.getEmail());
        userDto.setFollowersCount(
            user.getFollowers() == null ? 0 : user.getFollowers().size()
        );
        userDto.setFollowingsCount(
            user.getFollowings() == null ? 0 : user.getFollowings().size()
        );
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
