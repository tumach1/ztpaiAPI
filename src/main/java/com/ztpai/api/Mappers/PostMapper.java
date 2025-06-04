package com.ztpai.api.Mappers;

import com.ztpai.api.dao.MediaDao;
import com.ztpai.api.dao.PostDao;
import com.ztpai.api.dao.UserDao;
import com.ztpai.api.dto.MediaDto;
import com.ztpai.api.dto.PostDto;

import java.sql.Timestamp;
import java.util.Set;

public class PostMapper {
    public static PostDto toDto(PostDao post, boolean isSensitive) {
        if (post == null) {
            return null;
        }
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setContent(post.getContent());
        postDto.setTitle(post.getTitle());
        postDto.setCreationDate(post.getCreationDate()!= null ?
            post.getCreationDate().toLocalDateTime() : null);
        postDto.setCreator(UserMapper.toDto(post.getCreator(), isSensitive));
        postDto.setCreatorId(post.getCreator() != null ? post.getCreator().getId() : null);
        postDto.setMedia((post.getMedia().stream().map(MediaMapper::toDto).toList()));
        return postDto;
    }

    public static PostDao toDao(PostDto postDto) {
        if (postDto == null) {
            return null;
        }
        PostDao post = new PostDao();
        post.setId(postDto.getId());
        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        if(postDto.getCreator() != null) {
            post.setCreator(UserMapper.toDao(postDto.getCreator()));
        }
        if(postDto.getCreatorId() != null) {
            post.setCreator(new UserDao());
            post.getCreator().setId(postDto.getCreatorId());
        }
        return post;
    }
}
