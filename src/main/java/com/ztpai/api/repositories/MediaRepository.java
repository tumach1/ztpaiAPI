package com.ztpai.api.repositories;

import com.ztpai.api.dao.MediaDao;
import org.springframework.data.repository.CrudRepository;

public interface MediaRepository extends CrudRepository<MediaDao, Long> {
    MediaDao findById(long id);
    boolean existsById(long id);
    void deleteById(long id);
    MediaDao findByFilePath(String filePath);
    boolean existsByFilePath(String filePath);
    MediaDao save(MediaDao mediaDao);
    Iterable<MediaDao> findAll();
    Iterable<MediaDao> findAllByPostId(long postId);
}
