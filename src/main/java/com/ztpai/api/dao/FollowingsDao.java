package com.ztpai.api.dao;

import com.ztpai.api.utils.FollowingsKey;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "followings")
public class FollowingsDao {

    @EmbeddedId
    private FollowingsKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("followerId")
    @JoinColumn(name = "follower_id", nullable = false)
    private UserDao follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("creatorId")
    @JoinColumn(name = "creator_id", nullable = false)
    private UserDao creator;

    @Column(name = "follow_date", nullable = false)
    private Timestamp followDate;
}
