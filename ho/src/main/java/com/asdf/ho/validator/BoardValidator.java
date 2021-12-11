package com.asdf.ho.validator;

import org.springframework.stereotype.Component;

@Component
public class BoardValidator {
    public static void checkNull(String username, String title, String content, String categoryname, String img) {
        if(username.equals("")) {
            throw new IllegalArgumentException("아이디는 필수 입력 값 입니다");
        }
        if(title.equals("")) {
            throw new IllegalArgumentException("게시글 제목은 필수 입력 값 입니다");
        }
        if(content.equals("")) {
            throw new IllegalArgumentException("게시글 내용은 필수 입력 값 입니다");
        }
        if(categoryname.equals("")) {
            throw new IllegalArgumentException("카테고리는 필수 입력 값 입니다");
        }
        if(img.equals("")) {
            throw new IllegalArgumentException("이미지는 필수 입력 값 입니다");
        }

    }



}
