package com.ztpai.api.repositories;

import com.ztpai.api.dao.UserDao;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDao, Long> {
    UserDao findByUsername(String username);

    UserDao findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
