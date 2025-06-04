package com.ztpai.api.services;

import com.ztpai.api.dao.MediaDao;
import com.ztpai.api.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaService {
    @Autowired
    private MediaRepository mediaRepository;

    public MediaDao findById(long id) {
        return mediaRepository.findById(id);
    }

    public boolean existsById(long id) {
        return mediaRepository.existsById(id);
    }

    public void deleteById(long id) {
        mediaRepository.deleteById(id);
    }

    public MediaDao findByFilePath(String filePath) {
        return mediaRepository.findByFilePath(filePath);
    }

    public boolean existsByFilePath(String filePath) {
        return mediaRepository.existsByFilePath(filePath);
    }

    public MediaDao save(MediaDao mediaDao) {
        return mediaRepository.save(mediaDao);
    }

    public Iterable<MediaDao> findAll() {
        return mediaRepository.findAll();
    }

    public Iterable<MediaDao> findAllByPostId(long postId) {
        return mediaRepository.findAllByPostId(postId);
    }



}
