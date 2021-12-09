package com.asdf.ho.controller;



import com.asdf.ho.config.UserDetailsImpl;
import com.asdf.ho.dto.comment.CommentDto;
import com.asdf.ho.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;
    // 그냥 전부 userdetaills 가 존재하는지 여부에 따라 경우의 수를 나눠 주면 된다. 일종의 권한을 주는 작업임, 현재 userDetails로 들어가서 하는게 아니라서 작동이 불가능하다
    //댓글 저장
    @PostMapping("/posting/{id}/comment")
    public void registComment(@PathVariable Long id, @RequestBody CommentDto commentDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        commentService.saveComment(id, userId, commentDto);
    }

    //댓글 수정
    @PutMapping("/posting/{id}/comment/{commentid}")
    public void modifyComment(@PathVariable Long commentid,@RequestBody CommentDto commentDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.updateComment(commentid, commentDto, userDetails);
    }

    //댓글 삭제
    @DeleteMapping("/posting/{id}/comment/{commentid}")
    public void deleteComment(@PathVariable Long commentid,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.delete(commentid, userDetails);
    }


}
