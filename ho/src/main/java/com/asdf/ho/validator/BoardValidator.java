package com.asdf.ho.validator;


import com.asdf.ho.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
public class BoardValidator {
    private static BoardService boardService;
    public static void checkNull(String username, String title, String content, String categoryname) {
        if(username == null) {
            throw new IllegalArgumentException("아아디는 필수 입력 값 입니다");
        }
        if(title == null) {
            throw new IllegalArgumentException("게시글 제목은 필수 입력 값 입니다");
        }
        if(content == null) {
            throw new IllegalArgumentException("게시글 내용은 필수 입력 값 입니다");
        }
        if(categoryname == null) {
            throw new IllegalArgumentException("카테고리는 필수 입력 값 입니다");
        }
    }
    // 같은 아이디 조회수 업 방지 기능
    public static void viewCountUp(Long id, HttpServletRequest request, HttpServletResponse response) {

        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("view")) {
                    oldCookie = cookie;
                }
            }
        }

        if (oldCookie != null) {
            if (!oldCookie.getValue().contains("[" + id.toString() + "]")) {
                boardService.updateView(id);
                oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(oldCookie);
            }
        } else {
            boardService.updateView(id);
            Cookie newCookie = new Cookie("view","[" + id + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(newCookie);
        }
    }


}
