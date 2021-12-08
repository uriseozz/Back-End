package com.asdf.ho.dto.board;


import com.asdf.ho.dto.comment.CommentDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class DetailBoardResponseDto {

    private String username;
    private String title;
    private String content;
    private String img;
    private LocalDateTime modifiedAt;
    private List<CommentDto> comments;

    @Builder
    public DetailBoardResponseDto(String username, String title, String content, String img, LocalDateTime modifiedAt, List<CommentDto> comments) {
        this.username = username;
        this.title = title;
        this.content = content;
        this.img = img;
        this.modifiedAt = modifiedAt;
        this.comments = comments;
    }
}
