package com.asdf.ho.dto.category;


import com.asdf.ho.dto.board.BoardResponseDto;
import lombok.*;

import java.util.List;

@ToString
@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {
    private String categoryname;

    private List<BoardResponseDto> boards;

    @Builder
    public CategoryDto(String categoryname, List<BoardResponseDto> boards) {
        this.categoryname = categoryname;
        this.boards = boards;
    }
}
