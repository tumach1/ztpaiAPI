package com.ztpai.api.utils;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class FollowingsKey implements Serializable {
    @Column(name = "follower_id", nullable = false)
    private Long followerId;

    @Column(name = "creator_id", nullable = false)
    private Long creatorId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FollowingsKey that = (FollowingsKey) o;
        return Objects.equals(followerId, that.followerId) && Objects.equals(creatorId, that.creatorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(followerId, creatorId);
    }
}
