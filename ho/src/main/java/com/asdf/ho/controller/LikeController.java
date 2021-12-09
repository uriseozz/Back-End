package com.asdf.ho.controller;

import com.asdf.ho.config.UserDetailsImpl;
import com.asdf.ho.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/api/board/like/{boardid}")
    public void likeProc(@PathVariable Long boardid, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(userDetails == null){
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        likeService.likeProc(boardid,userDetails);
    }
}
