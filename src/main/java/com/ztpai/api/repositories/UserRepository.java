package com.ztpai.api.repositories;

import com.ztpai.api.dao.UserDao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserDao, Long> {
    Optional<UserDao> findByUsername(String username);

    UserDao findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<UserDao> findByUsernameContainingIgnoreCase(String username);
}
