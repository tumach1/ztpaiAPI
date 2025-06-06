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
    private String password;

    private int followersCount;
    private int followingsCount;
    private boolean isCreator;

    public void setIsCreator(boolean isCreator) {
        this.isCreator = isCreator;
    }
}
