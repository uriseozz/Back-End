package com.asdf.ho.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private String username;
    private String title;
    private String img;

    @Builder
    public BoardResponseDto(String username, String title, String img) {
        this.username = username;
        this.title = title;
        this.img =img;
    }
}
