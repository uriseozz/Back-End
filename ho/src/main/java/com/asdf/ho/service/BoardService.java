package com.asdf.ho.service;




import com.asdf.ho.config.UserDetailsImpl;
import com.asdf.ho.dto.board.BoardRequestDto;
import com.asdf.ho.dto.board.BoardResponseDto;
import com.asdf.ho.dto.board.DetailBoardResponseDto;
import com.asdf.ho.dto.comment.CommentDto;
import com.asdf.ho.entity.Board;
import com.asdf.ho.entity.Comment;
import com.asdf.ho.repository.BoardRepository;
import com.asdf.ho.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    //게시물 저장 메소드
    @Transactional
    public void saveBoard(BoardRequestDto boardRequestDto) {
        boardRepository.save(new Board(boardRequestDto));
    }
    //전체 게시물 출력 리스트
    @Transactional
    public List<BoardResponseDto> findPostingList() {
        List<BoardResponseDto> boardResponseDtos = new ArrayList<>();
        List<Board> boardList = boardRepository.findAllByOrderByModifiedAtDesc();
        for(Board board : boardList) {
            boardResponseDtos.add(board.toDto());
        }
        return boardResponseDtos;
    }

    //상세 게시물 페이지 조회 댓글 구현 포함
    @Transactional
    public DetailBoardResponseDto findPostingDetail(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        List<Comment> commentList = commentRepository.findByBoardId(id);
        List<CommentDto> comments = new ArrayList<>();
        for(int i=0;i<commentList.size(); i++) {
            comments.add(commentList.get(i).toDto());
        }
        return board.toDetailDto(comments);
    }

    //게시글 수정
    @Transactional
    public void updateBoard(Long id, BoardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(!userDetails.getUser().getUsername().equals(requestDto.getUsername())) {
            throw new IllegalArgumentException("작성자만 수정 가능합니다.");
        }
        Board board = boardRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        board.update(requestDto);
    }

    //게시글 삭제
    @Transactional
    public void deleteBoard(Long id, BoardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(!userDetails.getUser().getUsername().equals(requestDto.getUsername())) {
            throw new IllegalArgumentException("작성자만 삭제 가능합니다.");
        }
        boardRepository.deleteById(id);
    }


    //게시판 조회수 업데이트
    @Transactional
    public int updateView(Long id) {
        return boardRepository.updateView(id);
    }


}
