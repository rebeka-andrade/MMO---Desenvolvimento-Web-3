package com.devotics.MMORebekaEClarice.dto;

public class PostDTO {

    private Long id;
    private String content;
    private Long createdAt;
    private int likes;

    public PostDTO(Long id, String content, Long createdAt, int likes) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.likes = likes;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public int getLikes() {
        return likes;
    }
}