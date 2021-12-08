package com.asdf.ho.entity;



import com.asdf.ho.dto.comment.CommentDto;
import com.asdf.ho.validator.CommentValidator;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String commentcontent;

    @Column(nullable = false)
    private Long boardId;

    @Column(nullable = false)
    private Long userId;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @Builder
    public Comment(String username, String commentcontent, Long boardId, Long userId) {
        CommentValidator.checkNull(username, commentcontent, boardId, userId);
        this.username = username;
        this.commentcontent = commentcontent;
        this.boardId = boardId;
        this.userId = userId;
    }
    public void update(CommentDto commentDto) {
        CommentValidator.checkNull(commentDto.getUsername(), commentDto.getCommentcontent(),0L,0L);
        this.commentcontent = commentDto.getCommentcontent();
    }
    public CommentDto toDto() {
        return CommentDto.builder()
                .username(username)
                .commentcontent(commentcontent)
                .build();
    }



}
