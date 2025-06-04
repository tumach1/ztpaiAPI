package com.ztpai.api.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDao {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private java.util.Set<MediaDao> media = new java.util.HashSet<>();

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private UserDao creator;

    @Column(name = "creation_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private java.sql.Timestamp creationDate;

    @Column(name = "is_followers_only", nullable = false, columnDefinition = "boolean default false")
    private boolean isFollowersOnly;

}
