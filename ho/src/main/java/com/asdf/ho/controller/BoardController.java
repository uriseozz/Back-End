package com.asdf.ho.controller;



import com.asdf.ho.config.UserDetailsImpl;
import com.asdf.ho.dto.board.BoardRequestDto;
import com.asdf.ho.dto.board.BoardResponseDto;
import com.asdf.ho.dto.board.DetailBoardResponseDto;

import com.asdf.ho.service.BoardService;
import com.asdf.ho.service.CommentService;
import com.asdf.ho.validator.BoardValidator;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;

    //포스팅 입력받기
    @PostMapping("/posting")
    public String createPosting(@RequestBody BoardRequestDto boardRequestDto) {
        boardService.saveBoard(boardRequestDto);
        return "게시글 작성이 완료되었습니다.";
    }

    //포스팅 목록 보여주기
    @GetMapping("/user/main")
    public List<BoardResponseDto> getPostings() {
        return boardService.findPostingList();
    }

    //게시물 상세 페이지 보기 & 게시글 작성자 아이디로 조회시 조회수 안늘어남
    @GetMapping("/user/posting/{id}")
    public DetailBoardResponseDto getDetailPostings(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        if(userDetails==null) boardService.updateView(id);
        else boardService.viewCountUp(id,userDetails.getUser().getUsername());
        return boardService.findPostingDetail(id);
    }

    //게시글 수정하기
    @PutMapping("/posting/{id}")
    public void modifyPosting(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        boardService.updateBoard(id, boardRequestDto, userDetails);
    }

    //게시글 삭제하기
    @DeleteMapping("/posting/{id}")
    public void deletePosting(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        boardService.deleteBoard(id, userDetails);
    }
    //주석



}
