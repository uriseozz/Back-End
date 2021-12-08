package com.asdf.ho.service;



import com.asdf.ho.config.UserDetailsImpl;
import com.asdf.ho.dto.comment.CommentDto;
import com.asdf.ho.entity.Comment;
import com.asdf.ho.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    //댓글 저장 메소드
    @Transactional
    public void saveComment(Long id, Long userId, CommentDto commentDto) {
        commentRepository.save(commentDto.toEntity(id, userId));
    }

    //댓글 수정 메소드
    @Transactional
    public void updateComment(Long commentId, CommentDto commentDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(!userDetails.getUser().getUsername().equals(commentDto.getUsername())) {
            throw new IllegalArgumentException("작성자만 수정 가능합니다.");
        }
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        comment.update(commentDto);

    }

    //댓글 삭제 메소드
    @Transactional
    public void delete(Long commentId, CommentDto commentDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(!userDetails.getUser().getUsername().equals(commentDto.getUsername())) {
            throw new IllegalArgumentException("작성자만 삭제 가능합니다.");
        }
        commentRepository.deleteById(commentId);
    }
}
