package com.ztpai.api.web.controllers;

import com.ztpai.api.Mappers.PostMapper;
import com.ztpai.api.dao.PostDao;
import com.ztpai.api.dto.PostDto;
import com.ztpai.api.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        PostDao postDao = postService.findById(id);
        if (postDao.isFollowersOnly()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        if (postDao != null) {
            return ResponseEntity.ok(PostMapper.toDto(postDao, false));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping("/creator/{userId}")
    public ResponseEntity<Iterable<PostDto>> getPostsByCreatorId(@PathVariable  Long userId) {
        Iterable<PostDao> postDaos = postService.findAllByCreatorId(userId);
        if (postDaos != null) {
            List<PostDto> postDtos = new ArrayList<>();
            for (PostDao postDao : postDaos) {
                if (!postDao.isFollowersOnly()) {
                    postDtos.add(PostMapper.toDto(postDao, false));
                }
            }
            return ResponseEntity.ok(postDtos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable Long id) {
        if (postService.existsById(id)) {
            postService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PostMapping("/add")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        System.out.println("title: "+postDto.getTitle());
        PostDao postDao = PostMapper.toDao(postDto);

        System.out.println(postDao.getContent());
        PostDao createdPost = postService.save(postDao);
        if (createdPost == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        PostDto createdPostDto = PostMapper.toDto(createdPost, false);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPostDto);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long id, PostDto postDto) {
        postDto.setId(id);
        PostDao updatedPost = postService.save(PostMapper.toDao(postDto));
        if (updatedPost == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        PostDto updatedPostDto = PostMapper.toDto(updatedPost, false);
        return ResponseEntity.ok(updatedPostDto);
    }





}
