package com.asdf.ho.entity;

import com.asdf.ho.config.UserDetailsImpl;
import com.asdf.ho.dto.likes.LikeDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long boardId;

    @Column(nullable = false)
    private  Long userId;

    public Likes(Long id, UserDetailsImpl userDetails){
        this.boardId=id;
        this.userId=userDetails.getUser().getId();
    }
}
