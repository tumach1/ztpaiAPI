package com.ztpai.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private boolean isFollowersOnly;
    private Long creatorId;
    private UserDto creator;
    private LocalDateTime creationDate;
    private List<MediaDto> media;

}
