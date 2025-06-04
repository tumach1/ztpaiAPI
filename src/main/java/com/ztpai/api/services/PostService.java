package com.ztpai.api.services;

import com.ztpai.api.dao.PostDao;
import com.ztpai.api.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public PostDao findById(long id) {
        return postRepository.findById(id);
    }

    public boolean existsById(long id) {
        return postRepository.existsById(id);
    }

    public void deleteById(long id) {
        postRepository.deleteById(id);
    }

    public PostDao save(PostDao postDao) {
        return postRepository.save(postDao);
    }

    public Iterable<PostDao> findAllByCreatorId(long userId) {
        return postRepository.findAllByCreatorId(userId);
    }
}
