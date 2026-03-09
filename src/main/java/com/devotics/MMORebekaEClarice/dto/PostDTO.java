package com.devotics.MMORebekaEClarice.dto;

import lombok.Getter;
import lombok.Setter;
import com.devotics.MMORebekaEClarice.entity.Post;

@Getter
@Setter
public class PostDTO {
    private Long id;
    private String text;
    private String imageUrl;
    private String characterName;
    private String characterImageUrl;

    public PostDTO(Post post) {
        this.id = post.getId();
        this.text = post.getText();
        this.imageUrl = post.getImageUrl();
        this.characterName = post.getCharacter() != null ? post.getCharacter().getName() : "Desconhecido";
        this.characterImageUrl = post.getCharacter() != null ? post.getCharacter().getImageUrl() : null;
    }

}