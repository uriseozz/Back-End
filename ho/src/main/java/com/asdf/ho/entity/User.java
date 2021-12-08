package com.asdf.ho.entity;


import com.asdf.ho.validator.UserValidator;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@AllArgsConstructor
@Setter
public class User {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(unique = true)
    private Long kakaoId;


    @Builder
    public User(String email,String password,String username){
        UserValidator.checkForm(username, password, email);
        this.email=email;
        this.password=password;
        this.username=username;
        this.kakaoId = null;
    }


    public User(String username, String password, String email, Long kakaoId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.kakaoId = kakaoId;
    }

}


