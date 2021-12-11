package com.asdf.ho.entity;

import com.asdf.ho.dto.board.BoardRequestDto;
import com.asdf.ho.dto.board.BoardResponseDto;
import com.asdf.ho.dto.board.DetailBoardResponseDto;
import com.asdf.ho.dto.comment.CommentDto;
import com.asdf.ho.dto.likes.LikeDto;
import com.asdf.ho.validator.BoardValidator;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Setter
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

    @Column(nullable = false)
    private String img;

    @Column(nullable = false)
    private String categoryname;

    @Column
    private int view;

    @Column
    private int likeCnt;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public void update(BoardRequestDto boardRequestDto) {
        BoardValidator.checkNull(boardRequestDto.getUsername(), boardRequestDto.getTitle(), boardRequestDto.getContent(), boardRequestDto.getCategoryname(), boardRequestDto.getImg());
        this.username = boardRequestDto.getUsername();
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.img = boardRequestDto.getImg();
        this.categoryname = boardRequestDto.getCategoryname();
    }

    public Board(BoardRequestDto boardRequestDto) {
        BoardValidator.checkNull(boardRequestDto.getUsername(), boardRequestDto.getTitle(), boardRequestDto.getContent(), boardRequestDto.getCategoryname(), boardRequestDto.getImg());
        this.username = boardRequestDto.getUsername();
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.img = boardRequestDto.getImg();
        this.categoryname = boardRequestDto.getCategoryname();
    }

    public BoardResponseDto toDto() {
        return BoardResponseDto.builder()
                .id(id)
                .username(username)
                .title(title)
                .img(img)
                .view(view)
                .categoryname(categoryname)
                .likeCnt(likeCnt)
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
                .likeCnt(likeCnt)
                .build();

    }
    public LikeDto toLikeDto() {
        return LikeDto.builder()
                .boardId(id)
                .likeCnt(likeCnt)
                .build();

    }
    

}
