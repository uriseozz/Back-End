package com.asdf.ho.controller;


import com.asdf.ho.config.JwtTokenProvider;

import com.asdf.ho.entity.User;
import com.asdf.ho.repository.UserRepository;
import com.asdf.ho.service.KakaoUserService;
import com.asdf.ho.service.UserService;
import com.asdf.ho.dto.userdto.SignupRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final KakaoUserService kakaoUserService;


    //회원가입
    @PostMapping("/api/user/signup")
    public String userRegister(@RequestBody SignupRequestDto signupRequestDto){
        userService.userRegister(signupRequestDto);
        return "회원가입 성공";

    }

    //로그인 성공
    @PostMapping("/api/user/login")
    public List<Map<String,String>> login(@RequestBody SignupRequestDto requestDto) {
        User member = userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 username 입니다."));
        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        Map<String,String>username =new HashMap<>();
        Map<String,String>token = new HashMap<>();
        List<Map<String,String>> tu = new ArrayList<>(); // -> 리스트를 만드는데, Map형태(키:밸류 형태)의 변수들을 담을 것이다.
        token.put("token",jwtTokenProvider.createToken(Long.toString(member.getId()),member.getUsername())); // "username" : {username}
        username.put("username",member.getUsername()); // "token" : {token}
        tu.add(username); //List형태 ["username" : {username}]
        tu.add(token); //List형태 ["token" : {token}]
        return tu; // List형태 ["username" : {username}, "token" : {token}]
    }


    //로그인 성공
//    @PostMapping("/api/login")
//    public String login(@RequestBody Map<String, String> user) {
//        User member = userRepository.findByUsername(user.get("username"))
//                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 username 입니다."));
//        if (!passwordEncoder.matches(user.get("password"), member.getPassword())) {
//            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
//        }
//        return jwtTokenProvider.createToken(Long.toString(member.getId()),member.getUsername());
//    }

    //카카오 회원가입 및 로그인 요청 처리
    @GetMapping("/user/kakao/callback")
    public void kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code);
    }


}
