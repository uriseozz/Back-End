package com.asdf.ho.repository;


import com.asdf.ho.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByModifiedAtDesc();
    List<Board> findByCategoryname(String categoryname);


    // 게시판 조회수 기능 추가
//    @Modifying
//    @Query("update Board p set p.view = p.view + 1 where p.id = :id")
//    int updateView(Long id);


}
