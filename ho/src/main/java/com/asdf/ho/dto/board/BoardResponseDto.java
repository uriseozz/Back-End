package com.asdf.ho.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String username;
    private String title;
    private String img;
    //추가부분
    private int view;
    private String categoryname;
    private int likeCnt;

    @Builder
    public BoardResponseDto(Long id, String username, String title, String img, int view, String categoryname, int likeCnt) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.img =img;
        this.view = view;
        this.categoryname = categoryname;
        this.likeCnt = likeCnt;
    }
}
