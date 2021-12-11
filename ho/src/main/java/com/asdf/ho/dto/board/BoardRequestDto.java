package com.asdf.ho.dto.board;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Getter
@NoArgsConstructor
public class BoardRequestDto {
    private String username;
    private String title;
    private String content;
    private String img;
    private String categoryname;

}
