package com.devotics.MMORebekaEClarice.entities;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"follower_id", "following_id"})
})
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private GameCharacter follower;

    @ManyToOne
    @JoinColumn(name = "following_id")
    private GameCharacter following;

    public void setId(Long id) {
        this.id = id;
    }

    public void setFollower(GameCharacter follower) {
        this.follower = follower;
    }

    public void setFollowing(GameCharacter following) {
        this.following = following;
    }

    public Long getId() {
        return id;
    }

    public GameCharacter getFollower() {
        return follower;
    }

    public GameCharacter getFollowing() {
        return following;
    }
}