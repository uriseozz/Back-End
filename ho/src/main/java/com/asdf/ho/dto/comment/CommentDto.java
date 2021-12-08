package com.asdf.ho.dto.comment;



import com.asdf.ho.entity.Comment;
import lombok.*;


@ToString
@NoArgsConstructor
@Setter
@Getter
public class CommentDto {
    private String username;

    private String commentcontent;

    public Comment toEntity(Long boardId, Long userId) {
        return Comment.builder()
                .username(username)
                .commentcontent(commentcontent)
                .userId(userId)
                .boardId(boardId)
                .build();
    }
    @Builder
    public CommentDto(String username, String commentcontent) {
        this.username = username;
        this.commentcontent = commentcontent;
    }


}
