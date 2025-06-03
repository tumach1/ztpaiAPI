package com.ztpai.api.dto;

import java.util.Set;

public class UserDto {
    private Long id;
    private String username;
    private String email;
//    private String profilePictureUrl;
    private Set<UserDto> followers = new java.util.HashSet<>();
    private Set<UserDto> followings = new java.util.HashSet<>();

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getProfilePictureUrl() {
//        return profilePictureUrl;
//    }
//
//    public void setProfilePictureUrl(String profilePictureUrl) {
//        this.profilePictureUrl = profilePictureUrl;
//    }
//
    public Set<UserDto> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<UserDto> followers) {
        this.followers = followers;
    }

    public Set<UserDto> getFollowings() {
        return followings;
    }

    public void setFollowings(Set<UserDto> followings) {
        this.followings = followings;
    }
}
