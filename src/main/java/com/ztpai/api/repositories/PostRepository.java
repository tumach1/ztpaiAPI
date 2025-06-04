package com.ztpai.api.repositories;

import com.ztpai.api.dao.PostDao;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostDao, Long> {
    PostDao findById(long id);
    boolean existsById(long id);
    void deleteById(long id);
    Iterable<PostDao> findAllByCreatorId(long userId);
}
