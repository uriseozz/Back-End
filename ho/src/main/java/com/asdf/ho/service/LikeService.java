package com.asdf.ho.service;

import com.asdf.ho.config.UserDetailsImpl;
import com.asdf.ho.dto.likes.LikeDto;
import com.asdf.ho.entity.Board;
import com.asdf.ho.entity.Likes;
import com.asdf.ho.repository.BoardRepository;
import com.asdf.ho.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final BoardRepository boardRepository;

    //좋아요 상태여부
//    public boolean isLike(LikeDto likeDto){
//        Optional<Likes> found = likeRepository.findByBoardIdAndUserId(likeDto.getBoardId(),likeDto.getUserId());
//        if(found.isPresent()){
//            //좋아요 상태
//            return true;
//        }else {
//            return false;
//        }
//    }

    @Transactional
    public LikeDto likeProc(Long id, UserDetailsImpl userDetails) {
        Long boardId = id;
        Long userId = userDetails.getUser().getId();

        Board board = boardRepository.findById(boardId).orElseThrow(
                ()->new IllegalArgumentException("유효하지 않은 게시물입니다.")
        );
        Optional<Likes> found = likeRepository.findByBoardIdAndUserId(boardId,userId);
        //DB에 좋아요 데이터 있는지 확인
        if(found.isPresent()){
            likeRepository.deleteById(found.get().getId());
            //좋아요상태라면 ->안좋아요 상태로 변환
            board.setLikeCnt(board.getLikeCnt()-1);
        }else {
            //DB에 좋아요 데이터 없음
            Likes likes = new Likes(boardId,userDetails);
            //좋아요 상태로 변환(저장)
            likeRepository.save(likes);
            board.setLikeCnt(board.getLikeCnt()+1);
        }
        boardRepository.save(board);
        Board foundBoard = boardRepository.findById(boardId).orElseThrow(
                ()->new IllegalArgumentException("유효하지 않은 게시물입니다.")
        );
        return foundBoard.toLikeDto();

    }
}
