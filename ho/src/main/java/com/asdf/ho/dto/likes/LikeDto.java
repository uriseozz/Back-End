package com.asdf.ho.dto.likes;



import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LikeDto {
    private Long boardId;
    private int likeCnt;
    @Builder
    public LikeDto(Long boardId, int likeCnt) {
        this.boardId = boardId;
        this.likeCnt = likeCnt;
    }



}

