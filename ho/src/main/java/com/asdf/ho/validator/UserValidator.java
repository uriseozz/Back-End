package com.asdf.ho.validator;


import com.asdf.ho.dto.userdto.SignupRequestDto;
import com.asdf.ho.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Pattern;

@Component
public class UserValidator {
    public static void checkEmail(Optional<User> foundEmail) {
        if(foundEmail.isPresent()){
            throw new IllegalArgumentException ("중복된 이메일이 존재합니다.");
        }
    }
    public static void checkUserName(Optional<User> foundUsername) {
        if(foundUsername.isPresent()){
            throw new IllegalArgumentException ("중복된 아이디가 존재합니다.");
        }
    }
    public static void checkPassword(SignupRequestDto signupRequestDto) {
        if (!signupRequestDto.getPassword().equals(signupRequestDto.getPasswordCheck())) {
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }
    }
    public static void checkForm(String username, String password, String email) {
        if(username == null) {
            throw new IllegalArgumentException("아아디는 필수 입력 값 입니다");
        }
        if(password == null) {
            throw new IllegalArgumentException("비밀번호는 필수 입력 값 입니다");
        }
        if(email == null) {
            throw new IllegalArgumentException("이메일은 필수 입력 값 입니다");
        }

        if(!Pattern.matches("(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{3,}",username)) {
            throw new IllegalArgumentException("닉네임은 영문 대,소문자 또는 숫자가 1개 이상씩 포함된 3자이상이어야 합니다.");
        }
        if(!Pattern.matches(".{4,}",password)) {
            throw new IllegalArgumentException("비밀번호는 4자이상의 비밀번호여야 합니다.");
        }
        if(!Pattern.matches("^[a-z0-9A-Z._-]*@[a-z0-9A-Z]*.[a-zA-Z.]*$",email)) {
            throw new IllegalArgumentException("이메일 형식이 올바르지 않습니다.");
        }
        if(password.contains(username)) {
            throw new IllegalArgumentException("비밀번호에 아이디와 같은 값을 포함할 수 없습니다.");
        }
    }



}
