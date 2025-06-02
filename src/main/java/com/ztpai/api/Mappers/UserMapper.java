package com.ztpai.api.Mappers;

import com.ztpai.api.dao.UserDao;
import com.ztpai.api.dto.UserDto;

public class UserMapper {
    public static UserDto toDto(UserDao user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
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
