package com.devotics.MMORebekaEClarice.entities;

import org.apache.catalina.User;

import jakarta.persistence.*;

@Entity
@Table(name = "likes"
, uniqueConstraints = {
        @UniqueConstraint(columnNames = {"character_id", "post_id"})
})
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "character_id")
    private GameCharacter character;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public void setPost(Post post) {
        this.post = post;
    }

    public Post getPost() {
        return post;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCharacter(User user) {
        this.character = (GameCharacter) user;
    }

    public GameCharacter getCharacter() {
        return character;
    }

    
}