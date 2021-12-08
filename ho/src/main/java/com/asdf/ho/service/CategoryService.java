package com.asdf.ho.service;


import com.asdf.ho.dto.board.BoardResponseDto;
import com.asdf.ho.dto.board.DetailBoardResponseDto;
import com.asdf.ho.dto.category.CategoryDto;
import com.asdf.ho.entity.Board;
import com.asdf.ho.entity.Category;
import com.asdf.ho.repository.BoardRepository;
import com.asdf.ho.repository.CategoryRepository;
import com.asdf.ho.validator.CategoryValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final BoardRepository boardRepository;

    //카테고리 종류만 저장
    @Transactional
    public void saveCategory(String categoryname) {
        List<Category> categories = categoryRepository.findByCategoryname(categoryname);
        for (Category category : categories) {
            CategoryValidator.checkCategory(categoryname, category.getCategoryname());
        }
        categoryRepository.save(new Category(categoryname));
    }
    //카테고리 조회
    @Transactional
    public CategoryDto findCategories(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));
        List<Board> boards = boardRepository.findByCategoryname(category.getCategoryname());
        List<BoardResponseDto> boardResponseDtos = new ArrayList<>();
        for (int i=0; i<boards.size(); i++) {
            boardResponseDtos.add(boards.get(i).toDto());
        }
        return category.toDto(boardResponseDtos);
    }


}
