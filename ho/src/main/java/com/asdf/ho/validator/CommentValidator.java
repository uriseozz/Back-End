package com.asdf.ho.validator;


import org.springframework.stereotype.Component;

@Component
public class CommentValidator {
    public static void checkNull(String username, String commentcontent, Long boardId, Long userId) {
        if (username == null) {
            throw new IllegalArgumentException("아아디는 필수 입력 값 입니다");
        }
        if (commentcontent == null) {
            throw new IllegalArgumentException("댓글 내용은 필수 입력 값 입니다");
        }
        if (boardId == null) {
            throw new IllegalArgumentException("게시글 고유값은 필수 입력 값 입니다");
        }
        if (userId == null) {
            throw new IllegalArgumentException("아이디 고유값은 필수 입력 값 입니다");
        }
    }
}