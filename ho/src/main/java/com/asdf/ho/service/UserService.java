package com.asdf.ho.service;

import com.asdf.ho.entity.User;
import com.asdf.ho.repository.UserRepository;
import com.asdf.ho.dto.userdto.SignupRequestDto;
import com.asdf.ho.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;



@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;



    public String userRegister(SignupRequestDto signupRequestDto) {

        String email = signupRequestDto.getEmail();
        Optional<User> foundEmail = userRepository.findByEmail(email);
        //이메일 중복검사
        UserValidator.checkEmail(foundEmail);
        String username = signupRequestDto.getUsername();
        Optional<User> foundUsername = userRepository.findByUsername(username);
        //아이디 중복검사
        UserValidator.checkUserName(foundUsername);
        //패스워드 일치여부 검사
        UserValidator.checkPassword(signupRequestDto);
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        User user = new User(email,password,username);
        userRepository.save(user);
        return "Success Sign up";

    }


    public String checksId(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return "Existed Id";
        }
        return "You can use this Id";
    }

    public String checksEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return "Existed Email";
        }
        return "You can use this Email";
    }






}
