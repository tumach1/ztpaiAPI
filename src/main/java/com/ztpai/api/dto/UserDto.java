package com.ztpai.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private String email;
//    private String profilePictureUrl;
    private int followersCount;
    private int followingsCount;
}
