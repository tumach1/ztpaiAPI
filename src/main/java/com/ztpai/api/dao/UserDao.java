package com.ztpai.api.dao;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "is_creator", nullable = false)
    private boolean isCreator;

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private java.util.Set<FollowingsDao> followings;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private java.util.Set<FollowingsDao> followers;

    @Column(name = "is_admin", nullable = false)
    private boolean isAdmin;

    @Column(name = "is_moderator", nullable = false)
    private boolean isModerator;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "registration_date", nullable = false)
    private Timestamp registrationDate;
}
