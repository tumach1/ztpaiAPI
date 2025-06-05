package com.ztpai.api.services;

import com.ztpai.api.Mappers.UserMapper;
import com.ztpai.api.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ztpai.api.dao.UserDao;
import com.ztpai.api.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public UserDao findByUsername(String username) {
        UserDao user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return null;
        }
        return user;
    }

    public UserDao findByEmail(String email) {
        UserDao user = userRepository.findByEmail(email);
        return user;
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public UserDao save(UserDto userDto) {
        UserDao userDao = UserMapper.toDao(userDto);
        userDao.setPassword(encoder.encode(userDto.getPassword()));
        return userRepository.save(userDao);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
