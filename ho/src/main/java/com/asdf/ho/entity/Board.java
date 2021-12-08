package com.asdf.ho.entity;

import com.asdf.ho.dto.board.BoardRequestDto;
import com.asdf.ho.dto.board.BoardResponseDto;
import com.asdf.ho.dto.board.DetailBoardResponseDto;
import com.asdf.ho.dto.comment.CommentDto;
import com.asdf.ho.validator.BoardValidator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Board extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private String img;

    @Column(nullable = false)
    private String categoryname;

    @Column
    private int view;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public void update(BoardRequestDto boardRequestDto) {
        this.username = boardRequestDto.getUsername();
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.img = boardRequestDto.getImg();
        this.categoryname = boardRequestDto.getCategoryname();
    }

    public Board(BoardRequestDto boardRequestDto) {
        BoardValidator.checkNull(boardRequestDto.getUsername(), boardRequestDto.getTitle(), boardRequestDto.getContent(), boardRequestDto.getCategoryname());
        this.username = boardRequestDto.getUsername();
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.img = boardRequestDto.getImg();
        this.categoryname = boardRequestDto.getCategoryname();
    }

    public BoardResponseDto toDto() {
        return BoardResponseDto.builder()
                .username(username)
                .title(title)
                .img(img)
                .view(view)
                .build();
    }

    public DetailBoardResponseDto toDetailDto(List<CommentDto> comments) {
        return DetailBoardResponseDto.builder()
                .username(username)
                .title(title)
                .content(content)
                .img(img)
                .modifiedAt(modifiedAt)
                .comments(comments)
                .view(view)
                .build();

    }

    

}
