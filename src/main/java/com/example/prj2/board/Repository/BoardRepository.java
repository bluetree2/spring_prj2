package com.example.prj2.board.Repository;

import com.example.prj2.board.dto.BoardListInfo;
import com.example.prj2.board.entity.Todolist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Todolist, Integer> {

    List<BoardListInfo> findAllBy();

    Page<BoardListInfo> findAllBy(Pageable id);

    @Query("""
            SELECT b
            FROM Todolist b
            WHERE b.todoTitle LIKE :keyword
                OR b.todoContent LIKE :keyword
                OR b.username LIKE :keyword
            """)
    Page<BoardListInfo> searchByKeyword(String s, PageRequest id);
}