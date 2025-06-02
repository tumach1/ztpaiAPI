package com.ztpai.api.services;

import com.ztpai.api.Mappers.UserMapper;
import com.ztpai.api.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ztpai.api.dao.UserDao;
import com.ztpai.api.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDto findById(Long id) {
        return UserMapper.toDto(userRepository.findById(id).orElse(null));
    }
    public UserDto findByUsername(String username) {
        UserDao user = userRepository.findByUsername(username);
        return UserMapper.toDto(user);
    }

    public UserDto findByEmail(String email) {
        UserDao user = userRepository.findByEmail(email);
        return UserMapper.toDto(user);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public UserDao save(UserDto userDto) {
        return userRepository.save(UserMapper.toDao(userDto));
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
